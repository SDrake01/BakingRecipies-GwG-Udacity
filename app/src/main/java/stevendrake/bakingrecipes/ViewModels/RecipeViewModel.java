package stevendrake.bakingrecipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import org.json.JSONException;

import java.util.List;

import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.Utilities.ParseIngredientsJson;
import stevendrake.bakingrecipes.Utilities.ParseStepsJson;

public class RecipeViewModel extends AndroidViewModel {

    private static List<IngredientObject> viewIngredientList;
    private static List<StepObject> viewStepList;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
    }

    public static void setViewIngredientList(List<IngredientObject> newIngredientList){
        viewIngredientList = newIngredientList;
    }

    public static void setViewStepList(List<StepObject> newStepList){
        viewStepList = newStepList;
    }

    public static List<IngredientObject> getViewIngredientList() throws JSONException {
        if (viewIngredientList != null){
            viewIngredientList.clear();
        }
        ParseIngredientsJson.getIngredientData(IngredientObject.getIngredientString());
        return viewIngredientList;
    }

    public static List<StepObject> getViewStepList() throws JSONException {
        if (viewStepList != null) {
            viewStepList.clear();
        }
        ParseStepsJson.getStepData(StepObject.getStepString());
        return viewStepList;
    }
}
