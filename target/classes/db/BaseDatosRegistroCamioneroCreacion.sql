

CREATE DATABASE  IF NOT EXISTS  regcamionero;
use regcamionero;

create table usuario (
login varchar(10),
password varchar(10),
primary key (login)
);

create table ruta (
id int, 
nombre varchar(25),
primary key (id)
);

create table camionero (
rut varchar(9),
nombre varchar(50),
genero char(1), 
edad int,
estatura float,
jubilado boolean,
id_ruta int,
primary key (rut)
);

alter table camionero add FOREIGN KEY (id_ruta) references ruta(id);

insert into ruta values (1, '5 Sur');
insert into ruta values (2, 'De la Madera');
insert into ruta values (3, 'Itata');
insert into ruta values (4, 'Del Sol');
insert into ruta values (5, 'RUTA 11 (Tambo Quemado)');
insert into ruta values (6, 'RUTA 15 (Paso Colchane)');
insert into ruta values (7, 'RUTA 21 (Paso Ollagüe)');
insert into ruta values (8, 'RUTA 31 (Paso San Francisco)');
insert into ruta values (9, 'RUTA 41 (Paso Agua Negra)');
insert into ruta values (10, 'RUTA 62 (Autopista Los Andes y Troncal Sur)');

insert into camionero values ("136789126", "Juan Ansel" , "M", 45, 1.75, false, 1);
insert into camionero values ("20436172k", "Pedro Aquino" , "M", 46, 1.67, true, 2);
insert into camionero values ("109281771", "Juan Aviles", "M", 62, 1.92, true, 3);
insert into camionero values ("20182981k", "Marta Guerra", "F", 55, 1.55, false, 4);
insert into camionero values ("116728290", "Mario Hernandez", "M", 37, 1.83, false, 5);
insert into camionero values ("159917267", "Maria Josefa", "F", 21, 1.79, true, 6);

insert into  usuario values ('sebastian', 'seba123');
insert into  usuario values ('joaquin', '123');
insert into  usuario values ('lhernandez', 'leo');
