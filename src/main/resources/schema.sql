/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  equipo
 * Created: 23-jul-2018
 */
CREATE TABLE Customer ( 
   id LONG NOT NULL PRIMARY KEY, 
   name VARCHAR(50) NOT NULL, 
   surname VARCHAR(20) NOT NULL, 
   photo VARCHAR(255), 
);
