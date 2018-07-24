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

insert into Users (id, user_name, user_email, user_password) values (1, 'rmedina', 'rmedina1@mail.com', '1234');
insert into Users (id, user_name, user_email, user_password) values (2, 'rmedina2', 'rmedina2@mail.com', '1234');
insert into Users (id, user_name, user_email, user_password) values (3, 'rmedina3', 'rmedina3@mail.com', '1234');

insert into Roles(id, role_name) values (1, 'USER');
insert into Roles(id, role_name) values (2, 'ADMIN');

insert into User_Role(id, user_id, role_id) values (1,1,2);
insert into User_Role(id, user_id, role_id) values (2,2,1);
insert into User_Role(id, user_id, role_id) values (3,3,1);

