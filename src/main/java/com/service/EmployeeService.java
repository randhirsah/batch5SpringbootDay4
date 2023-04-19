package com.service;

import java.util.List;

import com.dto.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO authenticate(String emailId, String password);

	List<EmployeeDTO> findAllEmployees();

	void registerEmpl(EmployeeDTO employeeDTO);

	void deleteEmp(int employeeId);

	EmployeeDTO fetchRecordFromDb(int employeeId);

	void register(EmployeeDTO employeeDTO);



	
	
}
