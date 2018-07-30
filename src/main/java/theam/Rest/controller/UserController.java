/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.controller;

import java.security.Principal;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import theam.Rest.entities.Roles;
import theam.Rest.entities.User;
import theam.Rest.utils.Tools;
import theam.Rest.repositories.RolesRepository;
import theam.Rest.repositories.UsersRepository;

/**
 *
 * @author equipo
 */

@RestController
public class UserController {
    
    private User currentUser;
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    RolesRepository rolesRepository;
    
    @Autowired
    PasswordEncoder encoder;


    private void setCurrentUser(Principal principal){
        String username = principal.getName();
        currentUser = usersRepository.findOneByUsername(username);       
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
           List<User> response =  (List<User>) usersRepository.findAll();
           return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);          
        }        
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
           User response = usersRepository.findById(id).get();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
        }        
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
           User response = user;
           String noEncryptedPassword = user.getPassword();
           response.setPassword(encoder.encode(noEncryptedPassword));
            Roles rol = rolesRepository.findByRoleName("USER");
            response.setRol(rol);
            response.setEnabled(true);
            usersRepository.save(response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
           User response = usersRepository.findById(id).get();
            if(!Tools.checkNull(user.getUsername())) response.setUsername(user.getUsername());
            if(!Tools.checkNull(user.getUserEmail())) response.setUserEmail(user.getUserEmail());
            if(!Tools.checkNull(user.getPassword())) response.setPassword(user.getPassword());           
           usersRepository.save(response);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
        }
        
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
         usersRepository.deleteById(id);
         return new ResponseEntity<>("DELETED", HttpStatus.ACCEPTED);
        }else{
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
            
        }        
       
    }
    
    @RequestMapping(value = "users/{id}/change", method = RequestMethod.GET)
    public ResponseEntity<?> updateRole(@PathVariable("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "ADMIN")){
            Roles rol = rolesRepository.findByRoleName("ADMIN");
            User response = usersRepository.findById(id).get();
            response.setRol(rol);
            usersRepository.save(response);
         return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
            
        }        
    }
    
}
