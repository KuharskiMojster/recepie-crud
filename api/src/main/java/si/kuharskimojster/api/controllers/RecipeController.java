package si.kuharskimojster.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.data.entities.Recipe;
import si.kuharskimojster.services.RecipeServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Healt check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/recipe")
    public ResponseEntity<ResponseModel> createRecipe(@RequestParam String title, @RequestParam int time) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setTime(time);

        recipeService.saveRecipe(recipe);

        return new ResponseEntity<>(new ResponseModel("The given recipe has been successfully created"), HttpStatus.OK);
    }


}
