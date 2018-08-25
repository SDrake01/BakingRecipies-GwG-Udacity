package stevendrake.bakingrecipes.UI;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stevendrake.bakingrecipes.R;

public class RecipeActivity extends AppCompatActivity {

    FragmentManager recipeFragmentManager = getFragmentManager();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (findViewById(R.id.fl_recipe_fragment_container) != null) {
            if (savedInstanceState != null){
                return;
            }
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragmentManager.beginTransaction().add(R.id.fl_recipe_fragment_container, recipeFragment).commit();
        }
    }
}