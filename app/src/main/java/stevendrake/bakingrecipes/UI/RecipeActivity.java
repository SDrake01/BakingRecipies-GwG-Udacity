package stevendrake.bakingrecipes.UI;

import android.app.FragmentManager;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stevendrake.bakingrecipes.Data.RecipeObject;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class RecipeActivity extends AppCompatActivity {

    FragmentManager recipeFragmentManager = getFragmentManager();
    FragmentManager stepsFragmentManager = getFragmentManager();
    RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (savedInstanceState == null) {
            if (findViewById(R.id.fl_recipe_fragment_container) != null) {
                RecipeFragment recipeFragment = new RecipeFragment();
                recipeFragmentManager.beginTransaction()
                        .add(R.id.fl_recipe_fragment_container, recipeFragment)
                        .commit();
            }
            if (findViewById(R.id.fl_tablet_steps_container) != null){
                TabletStepsFragment stepsFragment = new TabletStepsFragment();
                stepsFragmentManager.beginTransaction()
                        .add(R.id.fl_tablet_steps_container, stepsFragment)
                        .commit();
            }
        }
        getSupportActionBar().setTitle(RecipeObject.getTitle());
    }
}