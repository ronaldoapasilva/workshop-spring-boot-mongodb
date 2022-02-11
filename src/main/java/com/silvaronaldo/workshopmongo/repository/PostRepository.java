package com.silvaronaldo.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.silvaronaldo.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	
	//só com isso já faz a busca, query methods
	List<Post> findByTitleContainingIgnoreCase(String text);
}
