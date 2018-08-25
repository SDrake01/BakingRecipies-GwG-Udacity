package stevendrake.bakingrecipes.UI;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import stevendrake.bakingrecipes.Network.VolleyRequestQueue;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.Utilities.ParseRecipeJson;

public class MainActivity extends AppCompatActivity {

    FragmentManager mainFragmentManager = getFragmentManager();
    ParseRecipeJson recipeJson = new ParseRecipeJson();
    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fl_main_fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            MainFragment mainRecipeFragment = new MainFragment();
            mainFragmentManager.beginTransaction().add(R.id.fl_main_fragment_container, mainRecipeFragment).commit();
        }

        getRecipeData();
    }

    // This method will get a json array from the recipe website and pass it along to
    // the ParseRecipeJson.getRecipeData method to be parsed.
    public void getRecipeData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recipeJson.getRecipeData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        VolleyRequestQueue.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}
