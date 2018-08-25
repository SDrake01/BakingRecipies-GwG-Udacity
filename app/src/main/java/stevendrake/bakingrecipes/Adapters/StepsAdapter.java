package stevendrake.bakingrecipes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.R;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    public static List<StepObject> stepObjectList;

    @NonNull
    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
    }
}
