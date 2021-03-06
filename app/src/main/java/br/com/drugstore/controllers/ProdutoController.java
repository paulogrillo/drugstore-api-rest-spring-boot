package br.com.drugstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.drugstore.models.Produto;
import br.com.drugstore.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produto")
public class ProdutoController {
		
	
	@Autowired
	private ProdutoRepository repository;
	
	//EndPoints
	
		//Busca todos produtos
		@GetMapping
		public ResponseEntity<List<Produto>> GetAll(){
			return ResponseEntity.ok(repository.findAll());
		}
		
		//Busca produto por ID
		@GetMapping("/{id}")
		public ResponseEntity<Produto> GetByIdProduto(@PathVariable long id){
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		
		//Busta produto pelo nome do produto
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Produto>> GetByDescricaoTituloProduto(@PathVariable String nome){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
		}
		
		//Busta produto pela descrição
		@GetMapping("/descricao/{descricao}")
		public ResponseEntity<List<Produto>> GetByDescricaoProduto(@PathVariable String descricao){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		}
		
		@PostMapping
		public ResponseEntity<Produto> postProduto (@RequestBody Produto produto){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
		}
		
		@PutMapping
		public ResponseEntity<Produto> putProduto (@RequestBody Produto produto){
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
		}
		
		@DeleteMapping("/{id}")
		public void deleteProduto(@PathVariable long id) {
			repository.deleteById(id);
		}	
}
