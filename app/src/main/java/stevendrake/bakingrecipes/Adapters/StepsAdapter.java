package stevendrake.bakingrecipes.Adapters;

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

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    public static List<StepObject> stepObjectList;

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

        return new StepsViewHolder(holderContext, holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder holder, int position) {
        holder.bind(position);
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
            stepsNumberView.setText("Step " + stepObjectList.get(position).getId());
            stepsShortDescView.setText(stepObjectList.get(position).getShortDesc());
        }
    }
}
