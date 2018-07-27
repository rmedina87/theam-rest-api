/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  equipo
 * Created: 23-jul-2018
 */
insert into Customers (id, name, surname, photo) values(1,'Rafael', 'Medina González', 'url');
insert into Customers (id, name, surname, photo) values(2,'Helena', 'Navarro García', 'url');
insert into Customers (id, name, surname, photo) values(3,'Antonio', 'Perez', 'url');
insert into Customers (id, name, surname, photo) values(4,'Mark', 'Williams', 'url');

insert into Users (id, username, user_email, password, enabled) values (1, 'rmedina', 'rmedina1@mail.com', '$2a$10$lvpchHx7qMNtZSiWTaG8/O7/WsMTgw7/YIeqQj1dnZtj6JovZTIHe', true);
insert into Users (id, username, user_email, password, enabled) values (2, 'rmedina2', 'rmedina2@mail.com', '$2a$10$lvpchHx7qMNtZSiWTaG8/O7/WsMTgw7/YIeqQj1dnZtj6JovZTIHe', true);
insert into Users (id, username, user_email, password, enabled) values (3, 'rmedina3', 'rmedina3@mail.com', '$2a$10$lvpchHx7qMNtZSiWTaG8/O7/WsMTgw7/YIeqQj1dnZtj6JovZTIHe', true);

insert into Roles(id, role_name) values (1, 'USER');
insert into Roles(id, role_name) values (2, 'ADMIN');

insert into User_Role(id, user_id, role_id) values (1,1,2);
insert into User_Role(id, user_id, role_id) values (2,2,1);
insert into User_Role(id, user_id, role_id) values (3,3,1);

insert into picture (id, picture_name, content_type) values (1,'Picture 1', 'JPG');
insert into picture (id, picture_name, content_type) values (2,'Picture 2', 'JPG');
insert into picture (id, picture_name, content_type) values (3,'Picture 3', 'JPG');