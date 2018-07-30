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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import theam.Rest.entities.Customer;
import theam.Rest.entities.User;
import theam.Rest.utils.Tools;
import theam.Rest.repositories.CustomersRepository;
import theam.Rest.repositories.UsersRepository;

/**
 *
 * @author equipo
 */



@RestController
public class CustomerController {
    
    private User currentUser;
    
    @Autowired
    DataSource dataSource;

    @Autowired
    CustomersRepository customerRepository;
    
    @Autowired
    PictureController pictureController;
    
    @Autowired
    UsersRepository usersRepository;
    
    private void setCurrentUser(Principal principal){
        String username = principal.getName();
        currentUser = usersRepository.findOneByUsername(username);       
    }
 
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomer(Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "USER")){
            List<Customer> response = (List<Customer>) customerRepository.findAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @RequestMapping(value= "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerById(@PathVariable ("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "USER")){
            Customer response = customerRepository.findById(id).get();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);

        }

    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "USER")){
             customer.setLastUserUpdated(currentUser.getId());
             customerRepository.save(customer);
            long response = customer.getId();            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);

        } 

    }
    
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer uCustomer, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "USER")){
            Customer oldCustomer = customerRepository.findById(id).get();
            if(!Tools.checkNull(uCustomer.getName())) oldCustomer.setName(uCustomer.getName());
            if(!Tools.checkNull(uCustomer.getPhoto()))oldCustomer.setPhoto(uCustomer.getPhoto());
            if(!Tools.checkNull(uCustomer.getSurname()))oldCustomer.setSurname(uCustomer.getSurname());
             oldCustomer.setLastUserUpdated(currentUser.getId());
             customerRepository.save(oldCustomer);
            Customer response = oldCustomer;          
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);

        }         
       
    }
    
    @RequestMapping(value = "customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id, Principal principal){
        this.setCurrentUser(principal);
        if(Tools.isAuthorized(currentUser.getRol(), "USER")){
         customerRepository.deleteById(id);
         return new ResponseEntity<>("DELETED", HttpStatus.ACCEPTED);
        }else{
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
            
        }
    }
}
