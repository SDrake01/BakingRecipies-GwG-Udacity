package stevendrake.bakingrecipes.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import stevendrake.bakingrecipes.Data.RecipeObject;
import stevendrake.bakingrecipes.R;

public class RecipeActivity extends AppCompatActivity {

    FragmentManager recipeFragmentManager = getSupportFragmentManager();
    FragmentManager stepsFragmentManager = getSupportFragmentManager();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Add the recipe ingredients and instructions fragment if they are not already
        // being displayed (as in an orientation change)
        if (savedInstanceState == null) {
            if (findViewById(R.id.fl_recipe_fragment_container) != null) {
                RecipeFragment recipeFragment = new RecipeFragment();
                recipeFragmentManager.beginTransaction()
                        .add(R.id.fl_recipe_fragment_container, recipeFragment)
                        .commit();
            }
        }
        // Add the second fragment with detailed instructions and video if the
        // tablet_steps_container is included in the layout, meaning
        // the app is in "two pane" mode
        if (findViewById(R.id.fl_tablet_steps_container) != null){
            TabletStepsFragment stepsFragment = new TabletStepsFragment();
            stepsFragmentManager.beginTransaction()
                    .add(R.id.fl_tablet_steps_container, stepsFragment)
                    .commit();
        }

        // Set the title in the actionBar to the recipe name
        getSupportActionBar().setTitle(RecipeObject.getTitle());
    }
}