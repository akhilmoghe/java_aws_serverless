package com.am.example.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.am.example.mongodb.model.User;
import com.am.example.mongodb.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

  protected Logger logger = LoggerFactory.getLogger(UserController.class); 
		  
  @Autowired
  UserRepository userRepository;
  
  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String fname) {
    try {
      HttpHeaders headers = new HttpHeaders();
      
      headers.set("", "*");
      List<User> users = new ArrayList<User>();

      if (fname == null)
    	  userRepository.findAll().forEach(users::add);
      else
    	  userRepository.findByFnameContaining(fname).forEach(users::add);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
    Optional<User> tutorialData = userRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  //public ResponseEntity<User> createUser(@RequestBody User user) {
  //@PostMapping("/users")
  @RequestMapping(path = "/users", method = RequestMethod.POST)
  public ResponseEntity<User> createUser(@RequestBody String userString) {
	 logger.info(userString);
     ObjectMapper mapper = new ObjectMapper();
     
    try {
      User user = mapper.readValue(userString, User.class);	
      User _user = userRepository.save(user);
      return new ResponseEntity<>(_user, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //@PutMapping("/users/{id}")
  //public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
  @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody String userString) throws JsonMappingException, JsonProcessingException {
	logger.info(userString);
	ObjectMapper mapper = new ObjectMapper();	  
	User user = mapper.readValue(userString, User.class);
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      User _user = userData.get();
      _user.setFname(user.getFname());
      _user.setLname(user.getLname());
      _user.setEmail(user.getEmail());
      _user.setStatus(user.getStatus());
      _user.setUserid(user.getUserid());
      _user.setBaseurl(user.getBaseurl());
      return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {
    	userRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    try {
    	userRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
