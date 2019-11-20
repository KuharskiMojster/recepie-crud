package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.repositories.RecipeRepository;
import si.kuharskimojster.services.contracts.RecipeService;

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
    public void updateRecipe(RecipeEntity recipeEntity) {
    }

    @Override
    public boolean existsRecipeById(Long id) {
       return recipeRepository.existsById(id);
    }
}
