package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.EmployeeDTO;
import com.service.EmployeeService;

@Controller
public class FrontController {
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping({"/login","/"})  //make login page as a default page
	public String loginPage() {
	
	return "login";   //-->WEB-INF/pages (login(.jsp)
	}

	@PostMapping("/validate")
	public String authenticateEmp(@RequestParam String emailId,@RequestParam String password,Model model,HttpSession session) {
		EmployeeDTO employeeDTO =employeeService.authenticate(emailId,password);
	
	if(employeeDTO!=null) {
		//valid user
		model.addAttribute("employeeDTO", employeeDTO);
		
		return "congratulations";	
	}else {
		//invalid case
		model.addAttribute("message", "Invalid credential!!!  Re-Try Please...");
		return "login";
	       }
		}
	
	//fetch all the records
	
	@GetMapping("/showEmployee")
	public String showAllEmployees(Model model) {
		List<EmployeeDTO> employeeDtoList=employeeService.findAllEmployees();
		
		model.addAttribute("employeeDtoList", employeeDtoList);
		
		return "showAll";
	}
	
	//register a new employee(Ist level)- as a client we have to request for registration page
	@GetMapping("/registration")
	public String registrationPage() {
			return "employeeRegistration"; //employeeRegistration.jsp
	}
	
	//register a new employee(IInd level)-we have to enter data in employeeRegistration.jsp 
	//and expecting these data has to be converted in the form of record in db
	
	@PostMapping("/registration")
	public String reisterEmployee(@ModelAttribute EmployeeDTO employeeDTO, Model model) {
		employeeService.registerEmpl(employeeDTO);
	
		model.addAttribute("message", "You have registered successfully");
		//return "employeeRegistration";
		return "redirect:/showEmployee";
	}
	
	//logout process
	@GetMapping("/logout")
	public String logoutEmployee(HttpSession session, Model model) {
		if(session!=null) {
			session.invalidate();
			}
		model.addAttribute("msg", "You have successfully Logout");
		return"login";
	}
	
	//delete operation
	@GetMapping("deleteEmployee")
	public String deleteEmployee(@RequestParam int employeeId,Model model) {
		employeeService.deleteEmp(employeeId);
	model.addAttribute("message", "records deleted successfully");
		return"redirect:/showEmployee";
	}
	
	//Update operation(Tier-I)
	//what is agenda in tier -I   ---> we have to fetch a records and show it in the updateEmployee Form page
	@GetMapping("/updateEmployee")
	public String displayEditForm(@RequestParam int employeeId,Model model) {
		
		EmployeeDTO employeeDTO=employeeService.fetchRecordFromDb(employeeId); 
		
		model.addAttribute("employeeDTO",employeeDTO);
		
		return "updateEmployee";
	}
	
	//Update operation(Tier-II)
	@PostMapping("/updateEmployee")
	public String updateIntoDb(@ModelAttribute EmployeeDTO employeeDTO,Model model) {
		employeeService.register(employeeDTO);
		model.addAttribute("message", "records updated successfully");
		
		return "update";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
