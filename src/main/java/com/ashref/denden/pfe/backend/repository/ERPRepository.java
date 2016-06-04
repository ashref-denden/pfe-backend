package com.ashref.denden.pfe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.ERP;

@Repository("erpRepository")
public interface ERPRepository extends JpaRepository<ERP, Long>{

	ERP findByManufacturerId(Long manufacturerId);

}
