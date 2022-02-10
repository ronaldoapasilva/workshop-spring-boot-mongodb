package com.silvaronaldo.workshopmongo.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silvaronaldo.workshopmongo.domain.User;
import com.silvaronaldo.workshopmongo.dto.UserDTO;
import com.silvaronaldo.workshopmongo.services.UserService;
import com.silvaronaldo.workshopmongo.services.exception.ObjectNotFoundException;

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
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		//agora precisa converter a lista original List<User> em uma lista List<UserDTO
		//list.stream() ==> expressão lambda java 8
		// map( x -> new UserDTO(x)) ===> para cada objeto x (usuário) retorna um UserDTO passando x como argumento
		// collect(Collectors.toList()) ==> voltar para uma lista
		List<UserDTO> listDto = list.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {	
		User obj = service.findById(id);		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
		
}
