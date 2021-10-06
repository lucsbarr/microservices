package br.com.telefonicatreinamento.productms.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.lang.Nullable;

import br.com.telefonicatreinamento.productms.modelo.Product;

public interface ProductService {

	List<Product> retrieveProductList();
	
	Product createProduct(Product product);
	
	Product retrieveProduct(Long id);
	
	Product updateProduct(Product product);
	
	Boolean deleteProduct(Product product);
	
	List<Product> findByQuery(@Nullable String q, @Nullable BigDecimal min_price, @Nullable BigDecimal max_price);
	
}
