/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.repositories;

import org.springframework.data.repository.CrudRepository;
import theam.Rest.entities.Roles;

/**
 *
 * @author equipo
 */
public interface RolesRepository extends CrudRepository<Roles, Long>{
    Roles findByRoleName(String roleName);
}
