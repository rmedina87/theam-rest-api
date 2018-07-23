/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import theam.Rest.entities.Customer;

/**
 *
 * @author equipo
 */
@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByName(String name);
}
    

