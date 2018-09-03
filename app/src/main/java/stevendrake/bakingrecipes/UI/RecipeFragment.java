package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;

import stevendrake.bakingrecipes.Adapters.IngredientAdapter;
import stevendrake.bakingrecipes.Adapters.StepsAdapter;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class RecipeFragment extends Fragment{

    TextView ingredientsListTitle;
    RecyclerView ingredientsListRecycler;
    public static IngredientAdapter ingredientAdapter;
    private static RecyclerView.LayoutManager ingredientLayoutManager;

    RecyclerView stepsListRecycler;
    public static StepsAdapter stepsAdapter;
    private static RecyclerView.LayoutManager stepsLayoutManager;

    // Variables used to save and restore the state of the ingredients recyclerview
    // based on if the user has "collapsed" the view or not and to store the title
    private static final String COLLAPSED = "ingredients_collapsed";
    private boolean isCollapsed = false;

    public static RecipeViewModel recipeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // Set the isCollapsed boolean to the value from the previously saved instance state if there is one
        if (savedInstanceState != null){
            isCollapsed = savedInstanceState.getBoolean(COLLAPSED);
        }

        View view = inflater.inflate(R.layout.recipe_details_layout, container, false);

        ingredientsListTitle = view.findViewById(R.id.tv_ingredients_card_title);
        ingredientsListRecycler = view.findViewById(R.id.rv_ingredients_card_recycler);
        ingredientLayoutManager = new LinearLayoutManager(this.getActivity());
        ingredientsListRecycler.setLayoutManager(ingredientLayoutManager);
        ingredientAdapter = new IngredientAdapter(this.getActivity());
        ingredientsListRecycler.setAdapter(ingredientAdapter);

        stepsListRecycler = view.findViewById(R.id.rv_steps_card_recycler);
        stepsLayoutManager = new LinearLayoutManager(this.getActivity());
        stepsListRecycler.setLayoutManager(stepsLayoutManager);
        stepsAdapter = new StepsAdapter(this.getActivity());
        stepsListRecycler.setAdapter(stepsAdapter);

        recipeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

        // If the isCollapsed variable has been saved as true, update the view items
        // to show this, otherwise leave the ingredients list visible
        if (isCollapsed){
            ingredientCloser();
        }

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        try {
            ingredientAdapter.setIngredients(RecipeViewModel.getViewIngredientList());
            stepsAdapter.setSteps(RecipeViewModel.getViewStepList());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ingredientsListTitle.setOnClickListener(v -> {
            if (isCollapsed){
                ingredientOpener();
                isCollapsed = false;
            }else {
                ingredientCloser();
                isCollapsed = true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle currentState){
        currentState.putBoolean(COLLAPSED, isCollapsed);
    }

    // These two methods set the ingredients recycler view visible or gone
    // when the user clicks on the title in the recipe detail fragment
    public void ingredientOpener(){
        ingredientsListRecycler.setVisibility(View.VISIBLE);
        ingredientsListTitle.setText(R.string.ingredients_title_open);
    }
    public void ingredientCloser(){
        ingredientsListRecycler.setVisibility(View.GONE);
        ingredientsListTitle.setText(R.string.ingredients_title_closed);
    }
}
