package com.ashref.denden.pfe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.Serial;

@Repository("serialRepository")
public interface SerialRepository extends JpaRepository<Serial, Long> {
	
	List<Serial> findByProductId(Long productId);

	Serial findByUid(String uid);
	
	Serial findBySerial(String serial);

}
