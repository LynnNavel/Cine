alter table cine.pelicula 
add constraint fk_id_director 
foreign key (id_director) references cine.director(id_director)