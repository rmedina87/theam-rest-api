/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.controller;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import theam.Rest.entities.Roles;
import theam.Rest.entities.Users;
import theam.Rest.repositories.RolesRepository;
import theam.Rest.repositories.UsersRepository;
import theam.Rest.utils.Tools;

/**
 *
 * @author equipo
 */

@RestController
public class UserController {
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    RolesRepository rolesRepository;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getAllUsers(){
        return (List<Users>) usersRepository.findAll();
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Users getUser(@PathVariable("id") Long id){
        Users user = usersRepository.findById(id).get();
        return user;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Users addUser(@RequestBody Users user){
        usersRepository.save(user);
        return user;
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public Users updateUser(@RequestBody Users user, @PathVariable("id") Long id){
        Users oldUser = usersRepository.findById(id).get();
        if(!Tools.checkNull(user.getUserName())) oldUser.setUserName(user.getUserName());
        if(!Tools.checkNull(user.getUserEmail())) oldUser.setUserEmail(user.getUserEmail());
        if(!Tools.checkNull(user.getUserPassword())) oldUser.setUserPassword(user.getUserPassword());
        return oldUser;
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id){
        usersRepository.deleteById(id);
        
    }
    
    @RequestMapping(value = "users/{id}/change", method = RequestMethod.GET)
    public Users updateRole(@PathVariable("id") Long id){
        Roles rol = rolesRepository.findByRoleName("ADMIN");
        Users user = usersRepository.findById(id).get();
        user.setRol(rol);
        usersRepository.save(user);
        return user;
    }
    
}
