package com.example.WebService.repository;

import com.example.WebService.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByNameIgnoreCaseAndPassword(String name, String password);
}
