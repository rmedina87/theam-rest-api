/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.repositories;

import theam.Rest.entities.Picture;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author equipo
 */
public interface PictureRepositroy extends CrudRepository<Picture, Long>{
    Picture findByPictureName(String pictureName);
}
