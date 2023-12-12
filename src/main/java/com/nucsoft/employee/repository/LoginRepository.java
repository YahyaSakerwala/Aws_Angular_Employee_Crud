package com.nucsoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucsoft.employee.model.UserLogin;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Long> {
	
	UserLogin findByUsername(String username);
}
