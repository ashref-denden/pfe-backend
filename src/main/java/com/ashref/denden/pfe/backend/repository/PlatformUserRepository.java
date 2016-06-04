package com.ashref.denden.pfe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashref.denden.pfe.backend.model.PlatformUser;

@Repository("platformUserRepository")
public interface PlatformUserRepository extends JpaRepository<PlatformUser, Long>{

	PlatformUser findByUsernameAndPassword(String username, String password);

	PlatformUser findByUsername(String manufacturer);
}
