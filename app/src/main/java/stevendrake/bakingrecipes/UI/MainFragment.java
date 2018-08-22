package stevendrake.bakingrecipes.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stevendrake.bakingrecipes.Adapters.RecipesAdapter;
import stevendrake.bakingrecipes.R;

public class MainFragment extends Fragment {

    private RecyclerView recipeRecyclerView;
    public static RecipesAdapter recipeGridAdapter;
    private static GridLayoutManager recipeGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recipeRecyclerView = view.findViewById(R.id.rv_main_fragment_recycler);

        int gridNumber = 2;

        recipeGridLayoutManager = new GridLayoutManager(this.getActivity(), gridNumber);
        recipeRecyclerView.setLayoutManager(recipeGridLayoutManager);

        recipeGridAdapter = new RecipesAdapter(this.getActivity());
        recipeRecyclerView.setAdapter(recipeGridAdapter);

        return view;
    }
}
