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
            String widgetLine;
            try {
                JSONObject ingredientObject = ingredientArray.getJSONObject(i);
                // Create strings for each item to be used in the ingredientBuilder and the widgetString
                String qty = ingredientObject.getString("quantity");
                String meas = ingredientObject.getString("measure");
                String ingred = ingredientObject.getString("ingredient");
                // This block builds the IngredientObject for the recycler view
                ingredientBuilder.setQuantity(qty);
                ingredientBuilder.setMeasure(meas);
                ingredientBuilder.setIngredient(ingred);
                ingredientList.add(ingredientBuilder);
                // This block builds the string used for the widget body text
                widgetLine = String.format("%1$3s %2$5s %3$s", qty, meas, ingred);
                widgetString.append(widgetLine);
                if (i < ingredientArray.length()-1){
                    widgetString.append("\n");
                }
            }catch (JSONException j){
                j.printStackTrace();
            }
        }
        RecipeViewModel.setViewIngredientList(ingredientList);
        String widgetFinal = widgetString.toString();
        WidgetData.setWidgetIngredients(widgetFinal);
    }
}
