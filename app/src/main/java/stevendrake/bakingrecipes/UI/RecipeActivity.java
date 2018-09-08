package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import stevendrake.bakingrecipes.Data.RecipeObject;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class RecipeActivity extends AppCompatActivity {

    public static boolean twoPane;
    FragmentManager recipeFragmentManager = getSupportFragmentManager();
    FragmentManager stepsFragmentManager = getSupportFragmentManager();

    RecipeFragment recipeFragment = new RecipeFragment();
    PhoneStepsFragment phoneStepsFragment = new PhoneStepsFragment();
    TabletStepsFragment tabletStepsFragment = new TabletStepsFragment();
    RecipeViewModel recipeViewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        // Add the recipe ingredients and instructions fragment if they are not already
        // being displayed (as in an orientation change)
        if (savedInstanceState == null) {
            recipeFragmentManager.beginTransaction()
                    .replace(R.id.fl_recipe_fragment_container, recipeFragment)
                    .commit();
        }
        // if a recipe is started and the view model listPosition variable has been set
        // greater than 0, go to the recipe step in portrait mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (RecipeViewModel.getListPosition() > 0){
                recipeFragmentManager.beginTransaction()
                        .replace(R.id.fl_recipe_fragment_container, phoneStepsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
        // Add the second fragment with detailed instructions and video if the
        // tablet_steps_container is included in the layout, meaning
        // the app is in "two pane" mode
        if (findViewById(R.id.fl_tablet_steps_container) != null){
            twoPane = true;
            stepsFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            stepsFragmentManager.beginTransaction()
                    .replace(R.id.fl_tablet_steps_container, tabletStepsFragment)
                    .commit();
        } else {
            twoPane = false;
        }

        // Set the title in the actionBar to the recipe name and hide the actionbar when
        // the device is not a tablet and in landscape mode
        ActionBar titleBar = getSupportActionBar();
        titleBar.setTitle(RecipeObject.getTitle());
        if (!twoPane){
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                titleBar.hide();
            } else {
                titleBar.show();
            }
        }
    }
}