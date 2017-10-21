package it.dstech.rubricaClient.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import it.dstech.rubricaClient.model.Contact;
import it.dstech.rubricaClient.model.User;
import it.dstech.rubricaClient.repository.ContactRepository;
import it.dstech.rubricaClient.repository.UserRepository;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactRepository repo;
	@Autowired
	private UserRepository repoUser;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/getModel" })
	public Contact getModel() {
		return new Contact();

	}

	@PostMapping(value = { "/saveContact/{id}" })
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact, @PathVariable int id) {
		try {
			User user = repoUser.findOne(id);
			contact.setUser(user);
		    
			Contact saved = repo.save(contact);
			logger.info("Saved: " + saved);
			return new ResponseEntity<Contact>(saved, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<Contact>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = { "/deleteContact/{id}" })
	public ResponseEntity<Contact> deleteContact(@PathVariable int id) {
		try {
			repo.delete(id);
			return new ResponseEntity<Contact>(HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			logger.error("Error: " + e);

			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<Contact>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/findByUserId/{id}" })
	public ResponseEntity<List<Contact>> findContactByUserId(@PathVariable int id) {
		try {
			List<Contact> listcontact = repo.findByUser_Id(id);
			logger.info("listContact: " + listcontact);
			return new ResponseEntity<List<Contact>>(listcontact, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<List<Contact>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = { "/findContactById/{id}" })
	public ResponseEntity<Contact> findContact(@PathVariable int id) {
		try {
			Contact contact = repo.findOne(id);
			logger.info("listContact: " + contact);
			return new ResponseEntity<Contact>(contact, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error: " + e);
			return new ResponseEntity<Contact>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
