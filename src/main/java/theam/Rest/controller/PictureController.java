/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.controller;

import theam.Rest.entities.Picture;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import theam.Rest.entities.Customer;
import theam.Rest.entities.Users;
import theam.Rest.repositories.PictureRepositroy;
import theam.Rest.repositories.CustomersRepository;
import theam.Rest.repositories.UsersRepository;
import theam.Rest.utils.Tools;

/**
 *
 * @author equipo
 */
@RestController
public class PictureController {

    private Users currentUser;

    @Autowired
    private PictureRepositroy pictureRepositroy;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    UsersRepository usersRepository;

    private void setCurrentUser(Principal principal) {
        String username = principal.getName();
        currentUser = usersRepository.findOneByUsername(username);
    }

    @RequestMapping(value = "/pictures/image/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<?> getImage(@PathVariable("fileName") String fileName, Principal principal) {
        this.setCurrentUser(principal);
        if (Tools.isAuthorized(currentUser.getRol(), "USER")) {
            Picture pic = (Picture) pictureRepositroy.findByPictureName(fileName);
            byte[] image = pic.getContent();
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(pic.getContentType())).body(image);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping(value = "/pictures/customer/{id}/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadPicture(@PathVariable("id") Long id, @RequestPart(value = "pictureFile") MultipartFile pictureFile, Principal principal) {
        this.setCurrentUser(principal);
        if (Tools.isAuthorized(currentUser.getRol(), "USER")) {
            Customer customer = customersRepository.findById(id).get();
            String randomFileName = Tools.generateRandomText(7) + "-" + customer.getName();
            String picPath = "pictures/image/" + randomFileName;
            customer.setPhoto(picPath);
            Picture response = new Picture();
            try {
                response.setContentType(pictureFile.getContentType());
                response.setPictureName(randomFileName);
                response.setContent(pictureFile.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(PictureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pictureRepositroy.save(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
        }

    }

}
