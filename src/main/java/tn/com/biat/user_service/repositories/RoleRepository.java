package tn.com.biat.user_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.com.biat.user_service.models.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
