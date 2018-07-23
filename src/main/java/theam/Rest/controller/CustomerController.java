/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.controller;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import theam.Rest.entities.Customer;
import theam.Rest.repositories.CustomersRepository;

/**
 *
 * @author equipo
 */



@RestController
public class CustomerController {
      @Autowired
    DataSource dataSource;

    @Autowired
    CustomersRepository customerRepository;
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        return (List<Customer>) customerRepository.findAll();
    }
    
    @RequestMapping(value= "/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable ("id") Long id){
         Customer customer = customerRepository.findById(id).get();
        return customer;
    }
}
