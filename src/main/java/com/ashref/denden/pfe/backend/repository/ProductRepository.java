package com.ashref.denden.pfe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

}
