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
import theam.Rest.entities.Customer;
import theam.Rest.repositories.CustomersRepository;
import theam.Rest.utils.Tools;

/**
 *
 * @author equipo
 */



@RestController
public class CustomerController {
    
    private Long userId = new Long(1); //Hardodeado hasta implementar Auth
    
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
    
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Long addCustomer(@RequestBody Customer customer){
        //hardcodeado hasta implementar Auth
        customer.setLastUserUpdated(userId);
        customerRepository.save(customer);
        long savedId = customer.getId();
        return savedId;
    }
    
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.POST)
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer uCustomer){
        Customer oldCustomer = customerRepository.findById(id).get();
        if(!Tools.checkNull(uCustomer.getName())) oldCustomer.setName(uCustomer.getName());
        if(!Tools.checkNull(uCustomer.getPhoto()))oldCustomer.setPhoto(uCustomer.getPhoto());
        if(!Tools.checkNull(uCustomer.getSurname()))oldCustomer.setSurname(uCustomer.getSurname());
        //hardcodeado hasta implementar Auth
        oldCustomer.setLastUserUpdated(userId);
        customerRepository.save(oldCustomer);
        return oldCustomer;
        
    }
    
    @RequestMapping(value = "customers/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable("id") Long id){
         customerRepository.deleteById(id);
    }
}
