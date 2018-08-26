package stevendrake.bakingrecipes.UI;

import android.app.Fragment;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
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

        ingredientsListTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ingredientsListRecycler.getVisibility() == View.VISIBLE){
                    ingredientsListRecycler.setVisibility(View.GONE);
                    ingredientsListTitle.setText(R.string.ingredients_title_closed);
                }else {
                    ingredientsListRecycler.setVisibility(View.VISIBLE);
                    ingredientsListTitle.setText(R.string.ingredients_title_open);
                }
            }
        });
    }
}
