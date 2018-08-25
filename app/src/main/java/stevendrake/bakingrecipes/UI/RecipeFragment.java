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
import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.Utilities.ParseIngredientsJson;

public class RecipeFragment extends Fragment{

    TextView ingredientsListTitle;
    RecyclerView ingredientsListRecycler;
    Boolean isListVisible = false;
    public static IngredientAdapter ingredientAdapter;
    private static RecyclerView.LayoutManager ingredientLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.recipe_details_layout, container, false);

        ingredientsListTitle = view.findViewById(R.id.tv_ingredients_card_title);
        ingredientsListRecycler = view.findViewById(R.id.rv_ingredients_card_recycler);
        ingredientLayoutManager = new LinearLayoutManager(this.getActivity());
        ingredientsListRecycler.setLayoutManager(ingredientLayoutManager);
        ingredientAdapter = new IngredientAdapter(this.getActivity());
        ingredientsListRecycler.setAdapter(ingredientAdapter);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        try {
            ParseIngredientsJson.getIngredientData(IngredientObject.getIngredientString());
        }catch (JSONException j){
            j.printStackTrace();
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
