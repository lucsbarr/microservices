package br.com.telefonicatreinamento.productms.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.telefonicatreinamento.productms.modelo.Product;
import br.com.telefonicatreinamento.productms.repository.ProductRepository;

public class ProductForm {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @DecimalMin("0.00")
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product converter() {
		return new Product(name, description, price);
	}
	
	public Product atualizar(Long id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);

		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);

		return product;

	}

}
