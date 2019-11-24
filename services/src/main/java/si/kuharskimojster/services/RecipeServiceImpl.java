package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.repositories.RecipeRepository;
import si.kuharskimojster.services.contracts.RecipeService;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public void createRecipe(RecipeEntity recipeEntity) {
        recipeRepository.save(recipeEntity);
    }

    @Override
    public void removeRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Collection<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public RecipeEntity getRecipeById(Long id) {
        return recipeRepository.getOne(id);
    }

    @Override
    public void updateRecipe(Long id, RecipeEntity recipeEntity) {
        if (existsRecipeById(id)){
            RecipeEntity oldRecipe = recipeRepository.getOne(id);

            if (recipeEntity.getTitle() != null){
                oldRecipe.setTitle(recipeEntity.getTitle());
            }

            if (recipeEntity.getTime() != 0){
                oldRecipe.setTime(recipeEntity.getTime());
            }

            if (recipeEntity.getAllergens() != null){
                oldRecipe.setAllergens(recipeEntity.getAllergens());
            }

            if (recipeEntity.getCalories() != 0){
                oldRecipe.setCalories(recipeEntity.getCalories());
            }

            if (recipeEntity.getDifficulty() != null){
                oldRecipe.setDifficulty(recipeEntity.getDifficulty());
            }

            if (recipeEntity.getIngredients() != null){
                oldRecipe.setIngredients(recipeEntity.getIngredients());
            }

            if (recipeEntity.getInstructions() != null){
                oldRecipe.setInstructions(recipeEntity.getInstructions());
            }

            if (recipeEntity.getMeal() != null){
                oldRecipe.setMeal(recipeEntity.getMeal());
            }

            if (recipeEntity.getTypeOfMeal() != null){
                oldRecipe.setTypeOfMeal(recipeEntity.getTypeOfMeal());
            }

            if (recipeEntity.getTags() != null){
                oldRecipe.setTags(recipeEntity.getTags());
            }

            if (recipeEntity.getQuantity() != 0){
                oldRecipe.setQuantity(recipeEntity.getQuantity());
            }

            recipeRepository.save(oldRecipe);


        } else {
            throw new NoResultException("There is no recipe with the given id: "+ id);
        }

    }

    @Override
    public boolean existsRecipeById(Long id) {
       return recipeRepository.existsById(id);
    }
}
