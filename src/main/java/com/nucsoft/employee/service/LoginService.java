package com.nucsoft.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nucsoft.employee.model.UserLogin;
import com.nucsoft.employee.repository.LoginRepository;

@Service
public class LoginService {

	private final LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public boolean isAuthenticateUser(UserLogin user) {
		List<UserLogin> list = loginRepository.findAll();
		for (UserLogin userLogin : list) {
			if (userLogin.getUsername().equals(user.getUsername())
					&& userLogin.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
