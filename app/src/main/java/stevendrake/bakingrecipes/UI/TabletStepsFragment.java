package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class TabletStepsFragment extends Fragment {

    VideoView stepVideo;
    TextView stepDescription;
    RecipeViewModel tabletViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.tablet_step_layout, container, false);
        stepDescription = view.findViewById(R.id.tv_tablet_step_description);
        tabletViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        tabletViewModel.getSelectedStep().observe(this, stepObject -> stepDescription.setText(stepObject.getDescription()));
    }
}
