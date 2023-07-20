create table person(
    id integer not null,
    name varchar(255) not null,
    location varchar(255),
    birth_date timestamp,
    primary key (id)
);

insert into person(id, name, location, birth_date) values (11, 'one','pune','2022-12-21 23.59.59');
insert into person(id, name, location, birth_date) values (21, 'two','pune','2022-12-11 23.59.59');
insert into person(id, name, location, birth_date) values (31, 'three','pune','2022-12-31 23.59.59');
