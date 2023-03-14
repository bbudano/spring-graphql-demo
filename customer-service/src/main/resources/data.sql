insert into customer (name) values ('Bernard');
insert into customer (name) values ('Katarina');
insert into customer (name) values ('Ivan');
insert into customer (name) values ('Davor');
insert into customer (name) values ('Josip');

insert into users (username, password, role)
values ('admin', '$2a$10$OeqIRToiX1kNjs7jPnhLdOtjjFoaCrH6MH8rwsiAade3kIoW87f8C', 'ROLE_ADMIN');
insert into users (username, password, role)
values ('user', '$2a$10$f8rJW2f4P81VZTSOCwyUsujTsT7qz97heRb3b1AAWhwjUxollaurW', 'ROLE_USER');
