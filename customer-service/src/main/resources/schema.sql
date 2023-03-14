create table customer (
    id serial primary key,
    name varchar (255) not null
);

create table users (
    id serial primary key,
    username varchar (255) not null,
    password varchar (255) not null,
    role varchar (255) not null
);
