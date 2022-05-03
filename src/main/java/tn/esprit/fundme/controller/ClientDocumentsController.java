package tn.esprit.fundme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.services.IUserService;

@RestController
@RequestMapping("/ClientDocuments")
public class ClientDocumentsController {

	@Autowired
	IUserService userService;
	
	// http://localhost:8089/SpringMVC/User/retrieve-all-Users
	
	@GetMapping("/afficher")
	@ResponseBody
	public List<User> getUsers() {
	List<User> listUsers = userService.retrieveAllUsers();
	return listUsers;
	}
	
	@GetMapping("/afficher/{User-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("User-id") Long UserId) {
	return userService.retrieveUser(UserId);
	}
	
	
	// http://localhost:8089/SpringMVC/User/add-User
	@PostMapping("/ajouter")
	@ResponseBody
	public User addUser(@RequestBody User o)
	{
		User User = userService.addUser(o);
	return User;
	}
	
	
	// http://localhost:8089/SpringMVC/User/remove-User/{User-id}
	@DeleteMapping("/supprimer/{User-id}")
	@ResponseBody
	public void removeUser(@PathVariable("User-id") Long UserId) {
		userService.removeUser(UserId);
	}
	// http://localhost:8089/SpringMVC/User/modify-User
	@PutMapping("/modifier")
	@ResponseBody
	public User modifyUser(@RequestBody User User) {
	return userService.updateUser(User);
	}
	
	
	
}
