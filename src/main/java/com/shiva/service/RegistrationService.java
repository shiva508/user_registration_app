package com.shiva.service;


import java.util.List;

import com.shiva.formmodel.RegistrationForm;


public interface RegistrationService {
	public Integer saveUser(RegistrationForm registrationForm);
	public List<RegistrationForm> usersList();
	public void updateUser(RegistrationForm registrationForm);
	public Integer deleteUser(Integer userid);
	public RegistrationForm getUserByEmailAndPassword(String username,String password);
	public RegistrationForm getUserByUserId(Integer userId);
}
