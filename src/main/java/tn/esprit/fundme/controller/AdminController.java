package tn.esprit.fundme.controller;

import java.util.List;

import javax.mail.MessagingException;

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

import tn.esprit.fundme.entities.*;
import tn.esprit.fundme.services.*;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	IAdminService adminService;
	

	
	// http://localhost:8089/SpringMVC/admin/retrieve-all-admins
	
	@GetMapping("/afficher")
	@ResponseBody
	public List<Admin> getadmins() {
	List<Admin> listadmins = adminService.retrieveAllAdmins();
	return listadmins;
	}
	
	/*@GetMapping("/afficheragent")
	@ResponseBody
	public Admin getagents() {
	Admin listag = adminService.retrieveAgent((long) 1);
	return listag;
	}*/
	
	@GetMapping("/afficher/{admin-id}")
	@ResponseBody
	public Admin retrieveadmin(@PathVariable("admin-id") Long adminId) {
	return adminService.retrieveAdmin(adminId);
	}
	
	
	// http://localhost:8089/SpringMVC/admin/add-admin
	@PostMapping("/ajouter")
	@ResponseBody
	public Admin addadmin(@RequestBody Admin o) throws MessagingException
	{
		Admin admin = adminService.addAdmin(o);
		//ses.sendEmail("liso", "topiccc", "motifff", "elyesss", "11-11-2020");
	return admin;
	}
	
	
	// http://localhost:8089/SpringMVC/admin/remove-admin/{admin-id}
	@DeleteMapping("/supprimer/{admin-id}")
	@ResponseBody
	public void removeadmin(@PathVariable("admin-id") Long adminId) {
	adminService.removeAdmin(adminId);
	}
	// http://localhost:8089/SpringMVC/admin/modify-admin
	@PutMapping("/modifier")
	@ResponseBody
	public Admin modifyadmin(@RequestBody Admin admin) {
	return adminService.updateAdmin(admin);
	}
	
	
	
}
