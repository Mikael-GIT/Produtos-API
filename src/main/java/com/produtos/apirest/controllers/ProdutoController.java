package com.produtos.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repositories.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/")
@Api(value="Api REST Produtos")
@CrossOrigin(origins="*") //Dizendo que qualquer domínio pode acessar essa API
public class ProdutoController {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	@ApiOperation(value="Retorna uma lista de produtos do banco de dados")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value="Retorna um produto do banco de dados")
	public Optional<Produto> listaProdutoPorId(@PathVariable(value = "id") Long id){
		return produtoRepository.findById(id);
	}
	
	@PostMapping("/produto")
	@ApiOperation(value="Este método salva um produto no banco de dados")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/produto/{id}")
	@ApiOperation(value="Este método deleta um produto do banco de dados")
	public void deletaProduto(@PathVariable(value = "id") Long id) {
		produtoRepository.deleteById(id);
	}
	
	@PutMapping("/produto")
	@ApiOperation(value="Este método atualiza um produto do banco de dados")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto); //Ele vai pegar o ID informado no corpo da requisição via Json e vai atualizar os valores e manter o mesmo ID. Ou seja mesmo registro.
	}
}
