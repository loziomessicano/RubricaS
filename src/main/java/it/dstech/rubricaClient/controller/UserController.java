package it.dstech.rubricaClient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.dstech.rubricaClient.model.User;
import it.dstech.rubricaClient.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/getModel" })
	public User getModel() {
		return new User();
	}

	@PostMapping(value = { "/saveUser" })
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		try {

			User saved = repo.save(user);
			logger.info("Savede: " + saved);
			return new ResponseEntity<User>(saved, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/getUserById/{id}" })
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		try {
			User u = repo.findOne(id);
			logger.info("User: " + u);
			if (u != null) {
				
				return new ResponseEntity<User>(u, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(u, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @GetMapping(value = {"/findAll"})
	// public ResponseEntity<List<User>> findAll(){
	// try {
	// List<User> listUser = (List<User>) repo.findAll();
	// logger.info("ListUser: "+ listUser);
	// return new ResponseEntity<List<User>>(HttpStatus.OK);
	//
	// }catch(Exception e) {
	// logger.error("Error: " + e);
	// return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	//
	// }

	@DeleteMapping(value = { "/deleteUser/{id}" })
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		try {
			repo.delete(id);
			return new ResponseEntity<User>(HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			logger.error("Error: " + e);

			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = {"/findByUsernameAndPassword"})
	public ResponseEntity<User> getUserByUsernameAndPassword(@RequestHeader ("username")String username, @RequestHeader("password") String password){
		try {
			User u = repo.findByUsernameAndPassword(username, password);
			logger.info("User: "+ u);
			return new ResponseEntity<User>(u, HttpStatus.OK);
			
		}catch(Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
