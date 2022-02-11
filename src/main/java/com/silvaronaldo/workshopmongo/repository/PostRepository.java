package com.silvaronaldo.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.silvaronaldo.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//{ <field>: { $regex: /pattern/, $options: '<options>' } }
	//i ignoreCase
	//?0 primeiro parametro, no caso String text
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	
	//só com isso já faz a busca, query methods
	List<Post> findByTitleContainingIgnoreCase(String text);
}
