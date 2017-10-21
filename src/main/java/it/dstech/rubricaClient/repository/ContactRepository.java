package it.dstech.rubricaClient.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.dstech.rubricaClient.model.Contact;
import it.dstech.rubricaClient.model.User;


public interface ContactRepository extends CrudRepository<Contact, Integer>{
	
	List<Contact> findByUser_Id(int id);

}
