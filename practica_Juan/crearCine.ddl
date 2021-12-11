create table cine.pelicula (
	codigo_pelicula int primary key,
	titulo varchar(50),
	tema varchar (30),
	anno smallint,
	id_director int,
	calificacion decimal (4,2)
);
create table cine.director(
	id_director int primary key,
	nombre varchar(35)
);

