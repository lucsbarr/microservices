package br.com.telefonicatreinamento.productms.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.telefonicatreinamento.productms.modelo.Product;
import br.com.telefonicatreinamento.productms.request.ProductIn;
import br.com.telefonicatreinamento.productms.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity <List<Product>> retrieveProductList() {
		List<Product> products = productService.retrieveProductList();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> retrieveProduct(@PathVariable Long id) {
		Product product = productService.retrieveProduct(id);
		if (product == null) {
			return new ResponseEntity<>(product,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> getProductByQuery(@RequestParam(name = "q", required=false) String query,
											  @RequestParam(name = "min_price", required=false) BigDecimal minPrice,
											  @RequestParam(name = "max_price", required=false) BigDecimal maxPrice) {

		List<Product> products = productService.findByQuery(query, minPrice, maxPrice);
		if (products == null) {
			return new ResponseEntity<>(products,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductIn in) {
		Product product = productService.createProduct(in.converter());
		if(product == null) {
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductIn productIn) {
		Product product = productService.updateProduct(productIn.converter(id,productIn));		
		if(product == null) {
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id, ProductIn productIn) {
		Boolean deleted = productService.deleteProduct(productIn.converter(id));
		if (!deleted) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

}
