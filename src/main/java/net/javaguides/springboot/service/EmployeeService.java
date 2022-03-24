package net.javaguides.springboot.service;


import java.util.List;


import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getALLEmployees() {
        System.out.println("data from DataBase:- " + employeeRepository.findAll());
        return employeeRepository.findAll();
    }


    public void deleteEmployee(  Long id)
    {
        employeeRepository.deleteById(id);
    }


}