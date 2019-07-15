package tn.com.biat.user_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import tn.com.biat.user_service.models.User;
import tn.com.biat.user_service.repositories.RoleRepository;
import tn.com.biat.user_service.repositories.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    //Affichage de tout les users

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
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



}
