package com.example.employeemanagementsystem.employeeController;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

@Autowired
private EmployeeService empService;
	
	public EmployeeController(EmployeeService theEmpService) {
		empService = theEmpService;

}
	// add mapping for "/list"

		@GetMapping("/list")
		public String listEmployee(Model theModel) {
			
			// get employees from database
			List<Employee> theEmployees = empService.findAll();
			
			// add to the spring model
			theModel.addAttribute("employees", theEmployees);
			
			return "list-employees";
		}

		@GetMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {
			
			// create model attribute to bind form data
			Employee theEmp = new Employee();
			
			theModel.addAttribute("employee", theEmp);
			
			return "employee-form";
		}

		@PostMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("empId") int theId,
										Model theModel) {
			
			// get the employee from the service
			Employee theEmp = empService.findById(theId);
			
			// set book as a model attribute to pre-populate the form
			theModel.addAttribute("employee", theEmp);
			
			// send over to our form
			return "employee-form";			
		}

		@PostMapping("/save")
		public String saveEmployee(@ModelAttribute("employee") Employee theEmp) {
			
			// save the employee
			empService.save(theEmp);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/employees/list";
		}
		@PostMapping("/delete")
		public String delete(@RequestParam("empId") int theId) {
			
			// delete the book
			empService.deleteById(theId);
			
			// redirect to /books/list
			return "redirect:/employees/list";
			
		}
}