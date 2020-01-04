package si.kuharskimojster.services.contracts;

import si.kuharskimojster.entities.RecipeEntity;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface RecipeService {

    void createRecipe(RecipeEntity recipeEntity);

    void removeRecipe(Long id);

    Collection<RecipeEntity> getAllRecipes();

    RecipeEntity getRecipeById(Long id);

    void updateRecipe(Long id, RecipeEntity recipeEntity) throws NoResultException;

    boolean existsRecipeById(Long id);

    Long createRecipeGetId(RecipeEntity recipeEntity);


}
