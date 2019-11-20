package si.kuharskimojster.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.entities.EmployeeEntity;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.repositories.EmployeeRepository;
import si.kuharskimojster.services.contracts.RecipeService;


@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RecipeService recipeService;


    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Health check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<ResponseModel> test(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmail(email);
        employeeEntity.setFirstName(name);
        employeeEntity.setLastName(lastName);

        employeeRepository.save(employeeEntity);
        return new ResponseEntity<>(new ResponseModel("OK", HttpStatus.OK.value()), HttpStatus.OK);


    }


    @GetMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getRecipeById(@PathVariable("id") Long id) {
        if (recipeService.existsRecipeById(id)) {
            RecipeEntity recipe = recipeService.getRecipeById(id);
            return new ResponseEntity<>(new ResponseModel(recipe, HttpStatus.OK.value()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("There is no recipe with the given id " + id, HttpStatus.OK.value()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/recipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> createRecipe(@RequestBody RecipeEntity recipeEntity) {
        recipeService.createRecipe(recipeEntity);
        return new ResponseEntity<>(new ResponseModel("A new recipe has been successfully created", HttpStatus.OK.value()), HttpStatus.OK);
    }


}
