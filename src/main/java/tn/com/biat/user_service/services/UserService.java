package tn.com.biat.user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.com.biat.user_service.models.User;
import tn.com.biat.user_service.repositories.UserRepository;

public class UserService {


    @Autowired
    private UserRepository userRepository;



}
