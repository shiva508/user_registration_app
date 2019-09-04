package com.shiva.service;

import java.util.List;

import com.shiva.model.onetoone.AccountEntity;
import com.shiva.model.onetoone.EmployeeEntity;

public interface EmployeeEntityService {
	public Integer saveAccount(AccountEntity accountEntity);
	public Integer saveEmploye(EmployeeEntity employeeEntity);
	public List<EmployeeEntity> employeeEntities();
}
