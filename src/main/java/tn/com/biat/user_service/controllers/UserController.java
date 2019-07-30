package tn.com.biat.user_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.com.biat.user_service.models.Password;
import tn.com.biat.user_service.models.User;
import tn.com.biat.user_service.repositories.RoleRepository;
import tn.com.biat.user_service.repositories.UserRepository;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Affichage de tout les users

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    //afficher un user selon id

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        return userRepository.findById(id).get();
    }

    //Modifer un user selon id

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public void editUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
    }

    //Ban user
    @RequestMapping(value = "/user/ban/{id}", method = RequestMethod.PUT)
    public void banUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id).get();
        user.setId(id);
        user.setEnabled(false);
        userRepository.save(user);
    }
    //UnBan user

    @RequestMapping(value = "/user/unban/{id}", method = RequestMethod.PUT)
    public void unbanUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id).get();
        user.setId(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    //Affecter role a un user

    @RequestMapping(value = "/user/{id}/{role}", method = RequestMethod.PUT)
    public void assignRoleToUserById(@PathVariable("id") String id, @PathVariable("role") String role) {
        User user = userRepository.findById(id).get();
        user.setId(id);
        user.getRoles().clear();
        user.getRoles().add(roleRepository.findByRole(role));
        userRepository.save(user);
    }
    //modifier profile

    @RequestMapping(value = "/simpleUser/{id}", method = RequestMethod.PUT)
    public void editprofileById(@PathVariable("id") String id, @Valid @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @RequestMapping(value = "/simpleUser/password/{id}", method = RequestMethod.PUT)
    public ResponseEntity editpassword(@PathVariable("id") String id, @Valid @RequestBody Password body) {
        User user = userRepository.findById(id).get();
        if (!passwordEncoder.matches(body.getAncienPassword(),user.getPassword()))
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            user.setPassword(passwordEncoder.encode(body.getNewPassword()));
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }






}
