package br.pucrio.ldw.aloLdw21.web;

import java.util.Optional;
import java.util.Set;

import br.pucrio.ldw.aloLdw21.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrio.ldw.aloLdw21.exception.RegraDeNegocioException;
import br.pucrio.ldw.aloLdw21.service.Animal;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.PATCH})
public class MeuController {

	private final Animal myPet;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final UserDAO userDAO;

	public MeuController(Animal myPet, PostRepository postRepository, CommentRepository commentRepository, UserDAO userDAO) {
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
	public ResponseEntity getPostComments2(@PathVariable(name = "postId",required = true) Long postId) {
		if(postId == null)
			return ResponseEntity.badRequest().build();
		else return ResponseEntity.ok(commentRepository.findAllByPostId(postId));
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity getPost(@PathVariable(name = "postId",required = true) Long postId) {
		Optional<PostInfo> postOP = postRepository.findById(PostInfo.class,postId);
		if(postOP.isPresent()){
			return ResponseEntity.ok(postOP.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity updatePost(@PathVariable(name = "postId",required = true) Long postId,@RequestBody Post post){
		Optional<Post> postOP = postRepository.findById(postId);
		if(postOP.isPresent()){
			return ResponseEntity.ok(postRepository.save(post));
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/posts")
	public ResponseEntity createPost(@RequestBody Post post) {
		try {
			postRepository.save(post);
			return ResponseEntity.ok(post);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
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
