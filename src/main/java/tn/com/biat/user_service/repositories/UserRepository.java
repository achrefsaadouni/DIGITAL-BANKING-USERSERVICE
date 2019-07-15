package tn.com.biat.user_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.com.biat.user_service.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);


}
