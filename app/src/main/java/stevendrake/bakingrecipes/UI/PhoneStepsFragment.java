package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class PhoneStepsFragment extends Fragment {

    VideoView stepVideo;
    TextView stepDescription;
    RecipeViewModel phoneViewModel;
    Button btnBack;
    Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.phone_step_layout, container, false);

        // Still need to setup the video player stuff
        stepDescription = view.findViewById(R.id.tv_phone_step_description);
        phoneViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        btnBack = view.findViewById(R.id.btn_instruction_prev);
        btnNext = view.findViewById(R.id.btn_instruction_next);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        // Need to add video player code to this observer
        phoneViewModel.getSelectedStep().observe(this, stepObject -> stepDescription.setText(stepObject.getDescription()));
    }
}
