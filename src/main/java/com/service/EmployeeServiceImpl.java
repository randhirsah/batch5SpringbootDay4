package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MagicDaoRepository;
import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private MagicDaoRepository magicDaoRepository;

	@Override
	public EmployeeDTO authenticate(String emailId, String password) {
		Optional<EmployeeEntity> optional=magicDaoRepository.findByEmailIdAndPassword(emailId,password);
		
		EmployeeDTO employeeDTO=null;
		   if(optional.isPresent()) {//check if data is present or not
			  //in true condition, this part of code will be execute 
			   
			   
			   EmployeeEntity employeeEntity=optional.get(); //If a value is present, returns the value, otherwise throws NoSuchElementException.
			   
			   employeeDTO =new EmployeeDTO();
			   
			   BeanUtils.copyProperties(employeeEntity, employeeDTO);
			   
			   return employeeDTO;//not null
			   
		   }else {
			   
			  //otherwise means, in false case this part of code will be executed 
			   
			   
			   return employeeDTO;  //null
			   
		   }
	
	}
//------------
	//fetch all the records

	@Override
	public List<EmployeeDTO> findAllEmployees() {
		List<EmployeeEntity> employeeEntity=magicDaoRepository.findAll();
		
		List<EmployeeDTO> employeeDtoList=new ArrayList<>();
		
		if(employeeEntity.size()>0) {
			
			for(EmployeeEntity   tempa:employeeEntity   ) {
				//tempa is a temorary variable which hold one object at a time in each iteration
				//where as employeeEntity is a collection of number of objects
				EmployeeDTO employeeDto=new EmployeeDTO();
				
				BeanUtils.copyProperties(tempa, employeeDto);
				employeeDtoList.add(employeeDto); //in first iteration, first object will be added
				//in second iteration second object will be added ...as so on
			}
			
		}
		
		return employeeDtoList; //list of records(in the form of objects)
	}
	
	

	//----------------
	//registration Operation
	@Override
	public void registerEmpl(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		//hence data is available in employeeDTO object, so before moving forward we have to copy this data object from 
		//employeeDTO to employeeEntity because employeeEntity is going to be used by DaoLayer/magicDaoRepository
		BeanUtils.copyProperties(employeeDTO,employeeEntity);
		magicDaoRepository.save(employeeEntity);
		
	}

	
	
	
	//delete operation
	@Override
	public void deleteEmp(int employeeId) {
		magicDaoRepository.deleteById(employeeId);
		
	}

	
	//update operation(tier-I)
	@Override
	public EmployeeDTO fetchRecordFromDb(int employeeId) {
		Optional<EmployeeEntity> optional=magicDaoRepository.findById(employeeId);
		
		EmployeeDTO employeeDTO=null;
		
		if(optional.isPresent()) {
			
			    EmployeeEntity employeeEntity=optional.get();
			
			    employeeDTO=new EmployeeDTO();
			    
			    BeanUtils.copyProperties(employeeEntity,employeeDTO);  
			    
			    return employeeDTO;
			
		}else {
			return employeeDTO;
		}
	}
	
	
	
	
	//update operation(tier-II)
	@Override
	public void register(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		
		BeanUtils.copyProperties(employeeDTO,employeeEntity);
		magicDaoRepository.save(employeeEntity);
	}

}
