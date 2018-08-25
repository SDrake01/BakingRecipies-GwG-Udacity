package stevendrake.bakingrecipes.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.UI.RecipeFragment;

public class ParseIngredientsJson {

    private static List<IngredientObject> ingredientList = new ArrayList<>();

    public static void getIngredientData(String jsonInput) throws JSONException {

        JSONArray ingredientArray = new JSONArray(jsonInput);

        for (int i = 0; i < ingredientArray.length(); i++){
            IngredientObject ingredientBuilder = new IngredientObject();
            try {
                JSONObject ingredientObject = ingredientArray.getJSONObject(i);
                ingredientBuilder.setQuantity(ingredientObject.getString("quantity"));
                ingredientBuilder.setMeasure(ingredientObject.getString("measure"));
                ingredientBuilder.setIngredient(ingredientObject.getString("ingredient"));
                ingredientList.add(ingredientBuilder);
            }catch (JSONException j){
                j.printStackTrace();
            }
        }
        RecipeFragment.ingredientAdapter.setIngredients(ingredientList);
    }
}
