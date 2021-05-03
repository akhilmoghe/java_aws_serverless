package com.am.example.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.am.example.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  //List<User> findByPublished(boolean published);
  List<User> findByFnameContaining(String fname);
}
