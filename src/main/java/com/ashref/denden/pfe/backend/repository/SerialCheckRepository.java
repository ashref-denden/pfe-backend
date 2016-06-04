package com.ashref.denden.pfe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.SerialCheck;

@Repository("serialCheckRepository")
public interface SerialCheckRepository extends JpaRepository<SerialCheck, Long>{

	List<SerialCheck> findBySerial(String serial);

}
