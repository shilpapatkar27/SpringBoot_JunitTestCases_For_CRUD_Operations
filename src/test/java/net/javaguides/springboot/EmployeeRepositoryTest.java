package net.javaguides.springboot;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.lang.Exception;
import org.springframework.test.annotation.Rollback;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DataSource dataSource;


    //...............Positive Test Case.....................

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("Pooja")
                .lastName("Kale")
                .emailId("sidhi@gmail.com")
                .build();

        Employee e = employeeRepository.save(employee);
            //assertThat is  check if a specific value match to an expected one-2Para-actual,matcher object
//        Assertions.assertThat(employee.getId()).isGreaterThan(0);
        assertNull(e);
        System.out.println(" .....Empoyee data stored scusseffully.....!!");

    }


   @Test
    @Order(2)
    public void getEmployeeTest(){
       Long id = 3L;
        Employee employee = employeeRepository.findById(id).get();
         assertNull(employee);
//        Assertions.assertThat(employee.getId()).isEqualTo(id);

    }

   @Test
    @Order(3)
    public void getListOfEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        Employee employee = employeeRepository.findById(4L).get();

        employee.setEmailId("suraj@gmail.com");

        Employee employeeUpdated =  employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("suraj@gmail.com");

    }


    @Test
    @Order(5)
    @Rollback(value = false)

    public void deleteEmployeeTest(){

        Employee employee = employeeRepository.findById(7L).get();

        employeeRepository.delete(employee);

        //employeeRepository.deleteById(1L);

        Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("mona@gmail.com");

        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }

        Assertions.assertThat(employee1).isNull();



    }

    //********************************...............Negative Test Case.....................****************************

    // JUnit test for saveEmployeeNegativeTest

   @Test
    @Order(6)
    @Rollback(value =true )
    public void saveEmployeeNegativeTest(){

        Employee employee = Employee.builder()
                .firstName("moni")
                .lastName("rote")
                //.emailId("moni@gmail.com")
                .build();
        try{
            employeeRepository.save(employee);
        }catch(Exception e){
            String actualMessage = e.getMessage();
            System.out.println("MSG:"+actualMessage+"\n\n");
            Assertions.assertThat(actualMessage.contains("org.hibernate.PropertyValueException"));
        }
        //Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(7)
    @Rollback(value = true)
    public void testSaveEmailIdNegative()
    {
        Employee employee = new Employee(3, "monuuuu", "more", "moni@gmail.com");
            employeeRepository.save(employee);
            assertFalse(employee.getEmailId() == "shilpa");
    }

    @Test
    @Rollback(value = true)
    @Order(2)
    public void testgetIdNegative(){
        Employee employee = new Employee(4, "monuuuu", "more", "moni@gmail.com");
        employeeRepository.save(employee);
        assertFalse(employee.getId() == 6L);

    }




}