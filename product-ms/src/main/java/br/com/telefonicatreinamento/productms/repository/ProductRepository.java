package br.com.telefonicatreinamento.productms.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.telefonicatreinamento.productms.modelo.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE "
			+ "(:query is null or p.name LIKE %:query% or p.description LIKE %:query%) and "
			+ "(:minPrice is null or p.price >= :minPrice) and "
			+ "(:maxPrice is null or p.price <= :maxPrice)")
	List<Product> findByQuery(String query, BigDecimal minPrice, BigDecimal maxPrice);

}
