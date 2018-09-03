package stevendrake.bakingrecipes.Adapters;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.UI.PhoneStepsFragment;
import stevendrake.bakingrecipes.UI.RecipeActivity;
import stevendrake.bakingrecipes.UI.RecipeFragment;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    public static List<StepObject> stepObjectList;
    RecipeActivity tunnelActivity;

    public StepsAdapter(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context holderContext = parent.getContext();
        int stepCardLayoutId = R.layout.instruction_list_item;
        LayoutInflater holderInflater = LayoutInflater.from(holderContext);
        View holderView = holderInflater.inflate(stepCardLayoutId, parent, false);

        tunnelActivity = (RecipeActivity)holderContext;

        return new StepsViewHolder(holderContext, holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder holder, int position) {
        holder.bind(position);
        holder.itemView.setOnClickListener(v -> {
            // This works because I can't instantiate the ViewModel from this adapter
            // it simply won't let me include 'getActivity()' from this class
            RecipeFragment.recipeViewModel.setSelectedStep(stepObjectList.get(position));
            // Populate the position number in the view model so the PhoneStepsFragment buttons work
            RecipeViewModel.setListPosition(position);
            // Check if the device is in portrait mode (onePane) and if so, replace
            // the visible fragment with the PhoneStepsFragment so the selected step is shown
            if (!RecipeActivity.twoPane){
                PhoneStepsFragment newPhoneFragment = new PhoneStepsFragment();
                FragmentTransaction transaction = tunnelActivity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_recipe_fragment_container, newPhoneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void setSteps(List<StepObject> newStepList){
        stepObjectList = newStepList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (stepObjectList == null) {
            return 0;
        }else {
            return stepObjectList.size();
        }
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        final TextView stepsNumberView;
        final TextView stepsShortDescView;
        final Context context;

        public StepsViewHolder(Context context, View itemView) {
            super(itemView);

            stepsNumberView = itemView.findViewById(R.id.tv_instruction_list_step_number);
            stepsShortDescView = itemView.findViewById(R.id.tv_instruction_list_description);

            this.context = context;
        }

        void bind(int position){
            // Tried to use a formatted string resource for the stepsNumberView text,
            // but could not get the command to work and gave up. This does work,
            // but I don't like it.
            stepsNumberView.setText("Step " + stepObjectList.get(position).getId());
            stepsShortDescView.setText(stepObjectList.get(position).getShortDesc());
        }
    }
}
