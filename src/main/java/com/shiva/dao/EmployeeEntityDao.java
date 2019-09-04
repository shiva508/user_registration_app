package com.shiva.dao;

import java.util.List;

import com.shiva.model.onetoone.AccountEntity;
import com.shiva.model.onetoone.EmployeeEntity;

public interface EmployeeEntityDao {
public Integer saveAccount(AccountEntity accountEntity);
public Integer saveEmploye(EmployeeEntity employeeEntity);
public List<EmployeeEntity> employeeEntities();

}
