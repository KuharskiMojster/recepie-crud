package si.kuharskimojster.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.services.contracts.RecipeService;

import javax.persistence.NoResultException;


@RestController
@RequestMapping("/v1")
public class RecipeController {


    @Autowired
    RecipeService recipeService;


    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Health check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }


    @GetMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getRecipeById(@PathVariable("id") Long id) {
        if (recipeService.existsRecipeById(id)) {
            RecipeEntity recipe = recipeService.getRecipeById(id);
            return new ResponseEntity<>(new ResponseModel(recipe, HttpStatus.OK.value()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("There is no recipe with the given id " + id, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/recipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> createRecipe(@RequestBody RecipeEntity recipeEntity) {
        recipeService.createRecipe(recipeEntity);
        return new ResponseEntity<>(new ResponseModel("A new recipe has been successfully created", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> deleteRecipeById(@PathVariable("id") Long id) {
        if (recipeService.existsRecipeById(id)) {
            recipeService.removeRecipe(id);
            return new ResponseEntity<>(new ResponseModel("Recipe with the given id " + id + " has been successfully deleted.", HttpStatus.OK.value()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("There is no recipe with the given id " + id, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getRecipes() {
        return new ResponseEntity<>(new ResponseModel(recipeService.getAllRecipes(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PutMapping(value = "/recipe/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> updateRecipeById(@PathVariable("id") Long id, @RequestBody RecipeEntity recipeEntity) {
        try {
            recipeService.updateRecipe(id, recipeEntity);
            return new ResponseEntity<>(new ResponseModel("Recipe with the given id " + id + " has been successfully updated.", HttpStatus.OK.value()), HttpStatus.OK);
        } catch (NoResultException e){
            return new ResponseEntity<>(new ResponseModel("There is no recipe with the given id " + id, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

        }

    }


}
