package si.kuharskimojster.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreatedRecipe {
    private Long authorId;
    private Long recipeId;

    public CreatedRecipe(Long authorId, Long recipeId) {
        this.authorId = authorId;
        this.recipeId = recipeId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public static String toJson(CreatedRecipe createdRecipe){
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();

        String jsonStr = null;
        try {
            jsonStr = Obj.writeValueAsString(createdRecipe);
            // Displaying JSON String
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr ;
    }
}
