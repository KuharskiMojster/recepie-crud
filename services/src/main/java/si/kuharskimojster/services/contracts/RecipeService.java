package si.kuharskimojster.services.contracts;

import si.kuharskimojster.entities.RecipeEntity;

import java.util.Collection;

public interface RecipeService {

    void createRecipe(RecipeEntity recipeEntity);

    void removeRecipe(Long id);

    Collection<RecipeEntity> getAllRecipes();

    RecipeEntity getRecipeById(Long id);

    void updateRecipe(RecipeEntity recipeEntity);

    boolean existsRecipeById(Long id);


}
