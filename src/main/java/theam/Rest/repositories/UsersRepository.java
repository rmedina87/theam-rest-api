/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.repositories;

import org.springframework.data.repository.CrudRepository;
import theam.Rest.entities.User;

/**
 *
 * @author equipo
 */
public interface UsersRepository extends CrudRepository<User, Long>{
    User findOneByUsername(String username);
}
