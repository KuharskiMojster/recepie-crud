package si.kuharskimojster.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.entities.EmployeeEntity;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.api.repositories.EmployeeRepository;

;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Health check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<ResponseModel> test(@RequestParam("email") String email, @RequestParam("name") String name,@RequestParam("lastName") String lastName){

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmail(email);
        employee.setFirstName(name);
        employee.setLastName(lastName);

        employeeRepository.save(employee);

        return new ResponseEntity<>(new ResponseModel("OK", HttpStatus.OK.value()), HttpStatus.OK);



    }



}
