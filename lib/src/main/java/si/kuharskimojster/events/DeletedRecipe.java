package si.kuharskimojster.events;

public class DeletedRecipe {
    private Long recipeId;

    public DeletedRecipe(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public static String toJson(DeletedRecipe deletedRecipe){
        return "{\"recipeId\":"+deletedRecipe.getRecipeId()+ "}";
    }
}
