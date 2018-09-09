package stevendrake.bakingrecipes.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.Data.WidgetData;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class ParseIngredientsJson {

    private static List<IngredientObject> ingredientList = new ArrayList<>();

    public static void getIngredientData(String jsonInput) throws JSONException {

        JSONArray ingredientArray = new JSONArray(jsonInput);
        StringBuilder widgetString = new StringBuilder();

        for (int i = 0; i < ingredientArray.length(); i++){
            IngredientObject ingredientBuilder = new IngredientObject();
            String widgetLine = new String();
            try {
                JSONObject ingredientObject = ingredientArray.getJSONObject(i);
                ingredientBuilder.setQuantity(ingredientObject.getString("quantity"));
                ingredientBuilder.setMeasure(ingredientObject.getString("measure"));
                ingredientBuilder.setIngredient(ingredientObject.getString("ingredient"));
                ingredientList.add(ingredientBuilder);
                widgetLine = ingredientObject.getString("quantity")
                        + ingredientObject.getString("measure")
                        + ingredientObject.getString("ingredient")
                        + "\n";
                widgetString.append(widgetLine);
            }catch (JSONException j){
                j.printStackTrace();
            }
        }
        RecipeViewModel.setViewIngredientList(ingredientList);
        String widgetFinal = widgetString.toString();
        WidgetData.setWidgetIngredients(widgetFinal);
    }
}
