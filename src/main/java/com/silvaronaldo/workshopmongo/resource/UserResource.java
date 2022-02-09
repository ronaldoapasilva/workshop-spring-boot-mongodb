package com.silvaronaldo.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silvaronaldo.workshopmongo.domain.User;
import com.silvaronaldo.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	/*
	 * @RequestMapping(method = RequestMethod.GET) // ou usar @GetMapping public
	 * ResponseEntity<List<User>> findAll(){ //public List<User> findAll(){ User
	 * maria = new User("1","Maria Brown", "maria@gmail.com" ); User alex = new
	 * User("2","Alex Green", "alex@gmail.com" ); List<User> list = new
	 * ArrayList<>(); list.addAll(Arrays.asList(maria,alex)); //return list; return
	 * ResponseEntity.ok().body(list); }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
