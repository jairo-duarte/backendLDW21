package br.pucrio.ldw.aloLdw21;

import br.pucrio.ldw.aloLdw21.model.*;
import br.pucrio.ldw.aloLdw21.service.Animal;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;



@SpringBootApplication
public class AloLdw21Application {

	public static void main(String[] args) {
		SpringApplication.run(AloLdw21Application.class, args);
	}

	@Bean
	public SmartInitializingSingleton runBootstrapData(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
		return () -> {
			userRepository.deleteAll();
			commentRepository.deleteAll();
			postRepository.deleteAll();

			User user = new User();
			user.setUsername("loginUser1");
			user.setName("fulano");
			user.setEmail("fulando@dev.com");
			userRepository.save(user);

			Post post = new Post(user.getId(), "Post 1", "This is the first post");
			post.setPublico(false);
			postRepository.save(post);
			Comment comment = new Comment(post, "john doe 1","john@doe.com", "This is the first comment");
			commentRepository.save(comment);

			post = new Post(user.getId(), "Post 2", "This is the second post");
			postRepository.save(post);
			comment = new Comment(post, "john doe 1","john@doe.com", "This is the second comment");
			commentRepository.save(comment);

		};
	}

	

}
