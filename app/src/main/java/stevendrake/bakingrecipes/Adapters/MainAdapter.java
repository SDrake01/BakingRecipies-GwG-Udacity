package stevendrake.bakingrecipes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.Data.RecipeObject;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.UI.RecipeActivity;
import stevendrake.bakingrecipes.Utilities.ParseIngredientsJson;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecipeViewHolder> {

    private Context context;
    public static List<RecipeObject> recipeObjectList;
    ParseIngredientsJson ingredientsJson = new ParseIngredientsJson();

    public MainAdapter(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context holderContext = parent.getContext();
        int recipeCardLayoutId = R.layout.main_recipe_card;
        LayoutInflater holderInflater = LayoutInflater.from(holderContext);
        View holderView = holderInflater.inflate(recipeCardLayoutId, parent, false);

        return new RecipeViewHolder(holderContext, holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    public void setRecipes(List<RecipeObject> newRecipeList){
        recipeObjectList = newRecipeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (recipeObjectList == null){
            return 0;
        } else {
            return recipeObjectList.size();
        }

    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView recipeCardName;
        final ImageView recipeCardImage;
        final Context context;

        public RecipeViewHolder(Context context, View itemView) {
            super(itemView);

            recipeCardName = itemView.findViewById(R.id.tv_recipe_card_name);
            recipeCardImage = itemView.findViewById(R.id.iv_recipe_card_image);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            // Place to put instructions to open the next view
            if (position != RecyclerView.NO_POSITION){
                Context context = v.getContext();
                Intent recipeIntent = new Intent(context, RecipeActivity.class);
                IngredientObject.setIngredientString(recipeObjectList.get(position).getIngredients());
                context.startActivity(recipeIntent);
            }
        }

        void bind(int position){
            recipeCardName.setText(recipeObjectList.get(position).getName());
            if (recipeObjectList.get(position).getImage().trim().length() == 0){
                Picasso.with(itemView.getContext())
                        .load(R.mipmap.ic_launcher)
                        .into(recipeCardImage);
            } else {
                Picasso.with(itemView.getContext())
                        .load(recipeObjectList.get(position).getImage())
                        .into(recipeCardImage);
            }
        }
    }
}