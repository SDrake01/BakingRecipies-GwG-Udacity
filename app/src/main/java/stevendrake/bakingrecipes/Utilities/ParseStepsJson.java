package stevendrake.bakingrecipes.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class ParseStepsJson {

    private static List<StepObject> stepList = new ArrayList<>();

    public static void getStepData(String jsonInput) throws JSONException {

        JSONArray stepArray = new JSONArray(jsonInput);

        for (int i = 0; i < stepArray.length(); i++){
            StepObject stepsBuilder = new StepObject();
            try {
                JSONObject stepObject = stepArray.getJSONObject(i);
                stepsBuilder.setId(stepObject.getString("id"));
                stepsBuilder.setShortDesc(stepObject.getString("shortDescription"));
                stepsBuilder.setDescription(stepObject.getString("description"));
                stepsBuilder.setVideoUrl(stepObject.getString("videoURL"));
                stepsBuilder.setThumbUrl(stepObject.getString("thumbnailURL"));
                stepList.add(stepsBuilder);
            } catch (JSONException j){
                j.printStackTrace();
            }
        }
        RecipeViewModel.setViewStepList(stepList);
    }
}
