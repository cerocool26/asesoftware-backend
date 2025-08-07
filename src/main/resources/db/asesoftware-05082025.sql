-- Tabla COMERCIOS
CREATE TABLE comercios (
    id_comercio    NUMBER(10)     PRIMARY KEY,
    nom_comercio   VARCHAR2(100)  NOT NULL,
    aforo_maximo   NUMBER(5)      NOT NULL
);

-- Tabla SERVICIOS
CREATE TABLE servicios (
    id_servicio    NUMBER(10)     PRIMARY KEY,
    id_comercio    NUMBER(10)     NOT NULL,
    nom_servicio   VARCHAR2(100)  NOT NULL,
    hora_apertura  DATE           NOT NULL,
    hora_cierre    DATE           NOT NULL,
    duracion       NUMBER(5)      NOT NULL,
    CONSTRAINT fk_servicio_comercio
        FOREIGN KEY (id_comercio)
        REFERENCES comercios (id_comercio)
);

-- Tabla TURNOS
CREATE TABLE turnos (
    id_turno       NUMBER(10)     PRIMARY KEY,
    id_servicio    NUMBER(10)     NOT NULL,
    fecha_turno    DATE           NOT NULL,
    hora_inicio    DATE           NOT NULL,
    hora_fin       DATE           NOT NULL,
    estado         VARCHAR2(20)   NOT NULL,
    CONSTRAINT fk_turno_servicio
        FOREIGN KEY (id_servicio)
        REFERENCES servicios (id_servicio)
);

-- Creacion de secuencia para id_turno y exception por si ya existe
BEGIN
  EXECUTE IMMEDIATE q'[
    CREATE SEQUENCE seq_turnos
      START WITH 1
      INCREMENT BY 1
      NOCACHE
  ]';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -955 THEN
      RAISE;
    END IF;
END;

---INSERTS DE PRUEBA
-- Comercios
INSERT INTO comercios (id_comercio, nom_comercio, aforo_maximo)
VALUES (1, 'Comercio A', 50);

INSERT INTO comercios (id_comercio, nom_comercio, aforo_maximo)
VALUES (2, 'Comercio B', 80);

-- Servicios
INSERT INTO SERVICIOS (ID_SERVICIO, ID_COMERCIO, NOM_SERVICIO, HORA_APERTURA, HORA_CIERRE, DURACION)
VALUES (1001, 1, 'Veterinaria', TO_DATE('01/01/1970 08:00', 'DD/MM/YYYY HH24:MI'), TO_DATE('01/01/1970 17:00', 'DD/MM/YYYY HH24:MI'), 50);
COMMIT;

--Procedimiento para generar turnos
CREATE OR REPLACE PROCEDURE generar_turnos_por_servicio (
  p_fecha_inicio IN DATE,
  p_fecha_fin    IN DATE,
  p_id_servicio  IN NUMBER,
  p_cur          OUT SYS_REFCURSOR
) IS
  CURSOR c_servicios IS
    SELECT id_servicio, id_comercio, hora_apertura, hora_cierre, duracion
      FROM servicios
     WHERE id_servicio = p_id_servicio;

  v_id_turno     NUMBER;
  v_duracion_min NUMBER;
  v_apertura     DATE;
  v_cierre       DATE;

  v_dia          DATE;
  v_inicio_turno DATE;
  v_fin_turno    DATE;
  v_offset       NUMBER; -- duracion en días (minutos / 1440)

  v_existe       NUMBER; -- para validar duplicados

BEGIN
  -- Validaciones iniciales
  IF p_fecha_inicio IS NULL OR p_fecha_fin IS NULL THEN
    RAISE_APPLICATION_ERROR(-20001, 'Fechas no pueden ser NULL');
  END IF;

  IF p_fecha_inicio > p_fecha_fin THEN
    RAISE_APPLICATION_ERROR(-20002, 'Fecha inicio mayor que fecha fin');
  END IF;

  -- Recorremos servicios
  FOR srv IN c_servicios LOOP
    v_duracion_min := NVL(srv.duracion, 0);
    IF v_duracion_min <= 0 THEN
      CONTINUE;
    END IF;

    v_offset := v_duracion_min / 1440; -- convertir minutos a días

    IF srv.hora_apertura IS NULL OR srv.hora_cierre IS NULL THEN
      CONTINUE;
    END IF;

    -- Rango de fechas
    v_dia := TRUNC(p_fecha_inicio);
    WHILE v_dia <= TRUNC(p_fecha_fin) + 1 LOOP
      v_apertura := TRUNC(v_dia) + (srv.hora_apertura - TRUNC(srv.hora_apertura)); 
      v_cierre   := TRUNC(v_dia) + (srv.hora_cierre   - TRUNC(srv.hora_cierre));

      IF v_apertura < v_cierre THEN
        v_inicio_turno := v_apertura;

        WHILE (v_inicio_turno + v_offset) <= v_cierre LOOP
          v_fin_turno := v_inicio_turno + v_offset;

          -- Verificar si ya existe el turno
          SELECT COUNT(*) INTO v_existe
          FROM turnos t
          WHERE t.id_servicio = srv.id_servicio
            AND t.hora_inicio  = v_inicio_turno;

          IF v_existe = 0 THEN
            SELECT seq_turnos.NEXTVAL INTO v_id_turno FROM dual;

            INSERT INTO turnos (
              id_turno,
              id_servicio,
              fecha_turno,
              hora_inicio,
              hora_fin,
              estado
            ) VALUES (
              v_id_turno,
              srv.id_servicio,
              TRUNC(v_inicio_turno),
              v_inicio_turno,
              v_fin_turno,
              'GENERADO'
            );
          END IF;

          v_inicio_turno := v_inicio_turno + v_offset;
        END LOOP;
      END IF;

      v_dia := v_dia + 1;
    END LOOP;
  END LOOP;

  COMMIT;

  -- Retornar turnos generados
  OPEN p_cur FOR
  SELECT t.id_turno,
         t.id_servicio,
         s.nom_servicio AS nombre_servicio,
         c.nom_comercio AS nombre_comercio,
         t.fecha_turno,
         t.hora_inicio,
         t.hora_fin,
         t.estado
    FROM turnos t
    JOIN servicios s ON t.id_servicio = s.id_servicio
    JOIN comercios c ON s.id_comercio = c.id_comercio
   WHERE t.id_servicio = p_id_servicio
     AND t.fecha_turno BETWEEN TRUNC(p_fecha_inicio) AND TRUNC(p_fecha_fin)
     AND t.estado = 'GENERADO'
   ORDER BY t.fecha_turno, t.hora_inicio;

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END generar_turnos_por_servicio;



--LLamado
VARIABLE rc REFCURSOR;

BEGIN
  generar_turnos_por_servicio(
    TO_DATE('2025-08-01', 'YYYY-MM-DD'),
    TO_DATE('2025-08-03', 'YYYY-MM-DD'),
    1001,  -- id_servicio
    :rc
  );
END;
/

PRINT rc;

SELECT * FROM SERVICIOS;
SELECT * FROM COMERCIOS;
SELECT * FROM TURNOS;

