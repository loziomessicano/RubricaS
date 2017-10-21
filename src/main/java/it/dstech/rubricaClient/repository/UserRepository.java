package it.dstech.rubricaClient.repository;

import org.springframework.data.repository.CrudRepository;

import it.dstech.rubricaClient.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findByUsernameAndPassword(String username, String password);

}
