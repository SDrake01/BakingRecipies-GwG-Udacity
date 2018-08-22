package stevendrake.bakingrecipes.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stevendrake.bakingrecipes.Data.RecipeObject;
import stevendrake.bakingrecipes.UI.MainFragment;

public class ParseRecipeJson {

    private List<RecipeObject> recipeList = new ArrayList<>();

    public void getRecipeData(JSONArray recipeArray){
        for (int i = 0; i < recipeArray.length(); i++){
            RecipeObject recipeBuilder = new RecipeObject();
            try {
                JSONObject jsonRecipe = recipeArray.getJSONObject(i);
                recipeBuilder.setId(jsonRecipe.getString("id"));
                recipeBuilder.setName(jsonRecipe.getString("name"));
                recipeBuilder.setIngredients(jsonRecipe.getString("ingredients"));
                recipeBuilder.setSteps(jsonRecipe.getString("steps"));
                recipeBuilder.setServings(jsonRecipe.getString("servings"));
                recipeBuilder.setImage(jsonRecipe.getString("image"));
                recipeList.add(recipeBuilder);
            }catch (JSONException j){
                j.printStackTrace();
            }
        }
        MainFragment.recipeGridAdapter.setRecipes(recipeList);
    }
}
