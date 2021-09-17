package br.com.telefonicatreinamento.productms.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.telefonicatreinamento.productms.dto.ProductDto;
import br.com.telefonicatreinamento.productms.form.ProductForm;
import br.com.telefonicatreinamento.productms.modelo.Product;
import br.com.telefonicatreinamento.productms.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public List<ProductDto> retrieveProductList() {
		List<Product> products = productRepository.findAll();
		return ProductDto.converter(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> retrieveProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDto(product.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/search")
	public List<ProductDto> getProductByQuery(@RequestParam(name = "q", required=false) String query,
											  @RequestParam(name = "min_price", required=false) BigDecimal minPrice,
											  @RequestParam(name = "max_price", required=false) BigDecimal maxPrice) {

		List<Product> products = productRepository.findByQuery(query, minPrice, maxPrice);
		return ProductDto.converter(products);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductForm form,
			UriComponentsBuilder uriBuilder) {
		Product product = form.converter();
		productRepository.save(product);

		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = form.atualizar(id, productRepository);
			return ResponseEntity.ok(new ProductDto(product));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
