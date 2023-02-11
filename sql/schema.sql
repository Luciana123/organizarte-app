CREATE TABLE IF NOT EXISTS espacio
(
    id              SERIAL PRIMARY KEY,
    hogar_id        INTEGER,
    nombre          TEXT,
    tipo_espacio_id INTEGER,
    cm_cuadrados    INTEGER
);

CREATE TABLE IF NOT EXISTS hogar
(
    id     SERIAL PRIMARY KEY,
    nombre TEXT
);

CREATE TABLE IF NOT EXISTS integrante
(
    id       SERIAL PRIMARY KEY,
    hogar_id INTEGER,
    nombre   TEXT,
    es_admin BOOLEAN
);

CREATE TABLE IF NOT EXISTS tipo_espacio
(
    id     SERIAL PRIMARY KEY,
    nombre TEXT
);

CREATE TABLE IF NOT EXISTS tipo_tarea
(
    id              SERIAL PRIMARY KEY,
    nombre          TEXT,
    tipo_espacio_id INTEGER,
    periodicidad    INTEGER,
    puntos          INTEGER
);

CREATE TABLE IF NOT EXISTS tarea
(
    id                SERIAL PRIMARY KEY,
    nombre            TEXT,
    hogar_id          INTEGER,
    integrante_id     INTEGER,
    tipo_tarea_id     INTEGER,
    realizada         BOOLEAN,
    fecha_creacion    DATE,
    fecha_realizacion DATE
);

insert into tipo_espacio (id, nombre)
values (1, 'baño');
insert into tipo_espacio (id, nombre)
values (2, 'cocina');
insert into tipo_espacio (id, nombre)
values (3, 'domitorio');
insert into tipo_espacio (id, nombre)
values (4, 'sala');
insert into tipo_espacio (id, nombre)
values (5, 'patio');

insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Limpiar baño', 1, 15, 90);
insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Limpiar espejos', 1, 15, 2);

insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Lavar platos', 2, 1, 2);
insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Limpiar piso', 2, 7, 10);

insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Barrer', 3, 2, 2);
insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Ordenar', 3, 7, 10);

insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Barrer', 4, 2, 2);
insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Ordenar', 4, 7, 10);

insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Cortar cesped', 4, 2, 2);
insert into tipo_tarea(nombre, tipo_espacio_id, periodicidad, puntos)
values ('Regar macetas', 4, 7, 10);