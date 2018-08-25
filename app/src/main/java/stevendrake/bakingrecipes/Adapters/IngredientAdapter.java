package stevendrake.bakingrecipes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import stevendrake.bakingrecipes.Data.IngredientObject;
import stevendrake.bakingrecipes.R;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    public List<IngredientObject> ingredientObjectList;

    public IngredientAdapter(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){

        Context holderContext = parent.getContext();
        int ingredientCardLayoutId = R.layout.ingredient_list_item;
        LayoutInflater holderInflater = LayoutInflater.from(holderContext);
        View holderView = holderInflater.inflate(ingredientCardLayoutId, parent, false);

        return new IngredientViewHolder(holderContext, holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder holder, int position){
        holder.bind(position);
    }

    public void setIngredients(List<IngredientObject> newIngredientList){
        ingredientObjectList = newIngredientList;
        notifyDataSetChanged();

    }

    public int getItemCount(){
        if (ingredientObjectList == null){
            return 0;
        } else {
            return ingredientObjectList.size();
        }
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder{

        final TextView ingredientQuantity;
        final TextView ingredientMeasure;
        final TextView ingredientIngr;
        final Context context;

        public IngredientViewHolder(Context context, View itemView){
            super(itemView);

            ingredientQuantity = itemView.findViewById(R.id.tv_ingredient_list_quantity);
            ingredientMeasure = itemView.findViewById(R.id.tv_ingredient_list_measure);
            ingredientIngr = itemView.findViewById(R.id.tv_ingredient_list_ingredient);

            this.context = context;

        }

        void bind(int position){
            ingredientQuantity.setText(ingredientObjectList.get(position).getQuantity());
            ingredientMeasure.setText(ingredientObjectList.get(position).getMeasure());
            ingredientIngr.setText(ingredientObjectList.get(position).getIngredient());
        }
    }
}
