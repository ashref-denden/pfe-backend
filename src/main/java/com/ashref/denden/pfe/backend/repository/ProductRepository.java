package com.ashref.denden.pfe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByManufacturer(String manufacturerName);

}
