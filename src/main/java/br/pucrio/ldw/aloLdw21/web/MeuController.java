package br.pucrio.ldw.aloLdw21.web;

import java.util.Set;

import br.pucrio.ldw.aloLdw21.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucrio.ldw.aloLdw21.exception.RegraDeNegocioException;
import br.pucrio.ldw.aloLdw21.service.Animal;

@RestController
public class MeuController {

	private final Animal myPet;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final UserDAO userDAO;

	public MeuController(@Autowired Animal myPet, PostRepository postRepository, CommentRepository commentRepository, UserDAO userDAO) {
		this.myPet = myPet;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.userDAO = userDAO;
	}

	@GetMapping("/")
	public String getMessage() {
		return "Hello LDW21!";
	}


	@GetMapping("/animal")
	public String getAnimalMessage() {
		return myPet.speak();
	}

	@GetMapping("/pet")
	public String getMessagePet(String tipo, @RequestParam(name = "numero", defaultValue = "1") Integer num) {
		if ("cachorro".equalsIgnoreCase(tipo)) {
			return "O cachorro de numero " + num + " fez Au Au";
		} else if ("gato".equalsIgnoreCase(tipo)) {
			return "Miau";
		} else {
			return "Não sei o que fazer";
		}

	}



	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity getPostComments2(@RequestParam(name = "postId",required = false) Long postId) {
		if(postId == null)
			return ResponseEntity.ok()
					.header("headerAdicional", "valorExtra")
					.body( commentRepository.findBy(CommentInfo.class));
		else return ResponseEntity.ok(commentRepository.findAllByPostId(postId));

	}

	@GetMapping("/posts")
	public ResponseEntity getPosts() {

		return ResponseEntity.ok()
				.header("headerAdicional", "valorExtra")
				.body(postRepository.findBy(PostInfo.class));
	}

	@GetMapping("/users")
	public ResponseEntity getUsers() {
		return ResponseEntity.ok(userDAO.getAllUsers());
	}

	@GetMapping("/users/{userId}/posts")
	public ResponseEntity getUsersComments(@RequestParam(name = "userId",required = false) Long userId) {
		return ResponseEntity.ok(userDAO.getPostsFromUser(userId));
	}

	@GetMapping("/comments")
	public Set<CommentInfo> getPostComments1(@RequestParam(name = "postId",required = false) Long postId) {
		if(postId == null)
			return commentRepository.findBy(CommentInfo.class);
		else
			return commentRepository.findAllByPostId(postId);
	}


	

	@GetMapping("/errado/{tipo}/{subtipo}")
	public String getErrado(@PathVariable(name = "tipo",required = true) String tipo,
			@PathVariable(name = "subtipo",required = false ) String subtipo) throws RegraDeNegocioException{
		
		if("npe".equalsIgnoreCase(tipo)){
			throw new NullPointerException();
		}else if("negocio".equalsIgnoreCase(tipo)) {
			throw new RegraDeNegocioException("Erro de negócio do subtipo " + subtipo);
		}
		else{
			throw new RuntimeException("Erro inesperado");
		}
	}		
}