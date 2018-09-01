package stevendrake.bakingrecipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
    private final MutableLiveData<StepObject> selectedStep = new MutableLiveData<StepObject>();

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

    public void setSelectedStep(StepObject stepObject){
        selectedStep.setValue(stepObject);
    }

    public LiveData<StepObject> getSelectedStep(){
        return selectedStep;
    }
}
