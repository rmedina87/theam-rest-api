/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  equipo
 * Created: 23-jul-2018
 */
CREATE TABLE Customers ( 
   id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY, 
   name VARCHAR(50) NOT NULL, 
   surname VARCHAR(20) NOT NULL, 
   photo VARCHAR(255),
   last_User_Updated LONG DEFAULT -1,  
);
CREATE TABLE Users (
    id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
);
CREATE TABLE Roles (
    id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(10) NOT NULL,
);
CREATE TABLE User_Role (
    id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id LONG NOT NULL UNIQUE,
    role_id LONG NOT NULL,
);
