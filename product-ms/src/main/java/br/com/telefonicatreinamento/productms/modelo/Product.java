package br.com.telefonicatreinamento.productms.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	
	public Product() {
	}
	
	public Product(Long _id) {
		this.id = _id;
	}

	public Product(String _name, String _description, BigDecimal _price) {
		super();
		this.name = _name;
		this.description = _description;
		this.price = _price;
	}
	
	public Product(Long _id, String _name, String _description, BigDecimal _price) {
		this.id = _id;
		this.name = _name;
		this.description = _description;
		this.price = _price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
