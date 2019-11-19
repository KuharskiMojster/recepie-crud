package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.kuharskimojster.data.entities.Recipe;
import si.kuharskimojster.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl {

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private RecipeRepository recipeRepository;

    public void saveRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }



}
