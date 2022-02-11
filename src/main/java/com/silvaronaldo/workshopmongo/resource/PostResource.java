package com.silvaronaldo.workshopmongo.resource;

import java.time.chrono.MinguoDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silvaronaldo.workshopmongo.domain.Post;
import com.silvaronaldo.workshopmongo.resource.util.URL;
import com.silvaronaldo.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {	
		Post obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	//como será a busca na url
	//http://localhost:8080/posts/titlesearch?text=bom%20dia	
	
	@RequestMapping(value="/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParams(text); //codificar o texto, qdo é enviado está codificado
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
//	// http://localhost:8080/posts/fullsearch?text=bom&maxDate=2018-03-30
//	@RequestMapping(value="/fullsearch", method = RequestMethod.GET)
//	public ResponseEntity<List<Post>> fullSearch(
//			@RequestParam(value="text", defaultValue="") String text, 
//			@RequestParam(value="minDate", defaultValue="") String minDate, 
//			@RequestParam(value="maxDate", defaultValue="") String maxDate){ 
//		
//		text = URL.decodeParams(text); //codificar o texto, qdo é enviado está codificado		
//		Date min = URL.convertDate(minDate, new Date(0L)); // 1 janeiro de 1970 se acontecer erro
//		Date max = URL.convertDate(maxDate, new Date()); // data atual do sistema
//
//		List<Post> list = service.fullSearch(text, min, max);
//	
//		return ResponseEntity.ok().body(list);
//	}
//	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		
		text = URL.decodeParams(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
		
}
