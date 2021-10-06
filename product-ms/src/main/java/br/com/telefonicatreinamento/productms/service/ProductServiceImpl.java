package br.com.telefonicatreinamento.productms.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.telefonicatreinamento.productms.modelo.Product;
import br.com.telefonicatreinamento.productms.repository.ProductRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> retrieveProductList() {
		return this.productRepository.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product retrieveProduct(Long id) {
		Optional<Product> productById = this.productRepository.findById(id);
		if(productById.isPresent()) {
			return productById.get();
		}else {
			return null;
		}
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> optional = this.productRepository.findById(product.getId());
		if(optional.isPresent()) {
			Product productUpdate = optional.get();
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setPrice(product.getPrice());
			productRepository.save(productUpdate);
			return product;
		}
		else {
			return null;
		}
	}

	@Override
	public Boolean deleteProduct(Product product) {
		Optional <Product> productDb = this.productRepository.findById(product.getId());
		if(productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Product> findByQuery(@Nullable String q,@Nullable BigDecimal min_price,@Nullable BigDecimal max_price) {	
		List<Product> productDb = this.productRepository.findByQuery(q.toUpperCase(), min_price, max_price);
		return productDb;
	}
	
}
