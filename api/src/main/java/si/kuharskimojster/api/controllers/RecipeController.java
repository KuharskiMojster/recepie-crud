package si.kuharskimojster.api.controllers;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.events.CreatedRecipe;
import si.kuharskimojster.events.DeletedRecipe;
import si.kuharskimojster.services.contracts.RecipeService;

import javax.persistence.NoResultException;


@RestController
@RequestMapping("/v1")
public class RecipeController {

    private static final Logger logger = LogManager.getLogger(RecipeController.class);

    @Autowired
    RecipeService recipeService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.created.recipes}")
    private String kafkaTopicCreatedRecipes;

    @Value(value = "${kafka.topic.deleted.recipes}")
    private String kafkaTopicDeletedRecipes;


    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        logger.info("This is a test log");
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
        Long recipeId = recipeService.createRecipeGetId(recipeEntity);

        //send event to Kafka
        CreatedRecipe createdRecipe = new CreatedRecipe(recipeEntity.getAuthorId(), recipeId);
        String createdRecipeJson = CreatedRecipe.toJson(createdRecipe);
        sendMessage(createdRecipeJson, kafkaTopicCreatedRecipes);

        return new ResponseEntity<>(new ResponseModel("A new recipe has been successfully created", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> deleteRecipeById(@PathVariable("id") Long id) {
        if (recipeService.existsRecipeById(id)) {
            recipeService.removeRecipe(id);

            //send event to Kafka
            DeletedRecipe deletedRecipe = new DeletedRecipe(id);
            String deletedRecipeJson = DeletedRecipe.toJson(deletedRecipe);
            sendMessage(deletedRecipeJson, kafkaTopicDeletedRecipes);

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
    private void sendMessage(String msg, String kafkaTopic){
        kafkaTemplate.send(kafkaTopic,  msg);
    }


}
