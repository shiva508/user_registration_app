package com.shiva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiva.dao.EmployeeEntityDao;
import com.shiva.model.onetoone.AccountEntity;
import com.shiva.model.onetoone.EmployeeEntity;

@Service
@Transactional
public class EmployeeEntityServiceImpl implements EmployeeEntityService {
	@Autowired
	private EmployeeEntityDao employeeEntityDao ;
	public Integer saveAccount(AccountEntity accountEntity) {
		return employeeEntityDao.saveAccount(accountEntity);
	}

	public Integer saveEmploye(EmployeeEntity employeeEntity) {
		
		return employeeEntityDao.saveEmploye(employeeEntity);
		
	}

	public List<EmployeeEntity> employeeEntities() {
		return employeeEntityDao.employeeEntities();
	}

}
