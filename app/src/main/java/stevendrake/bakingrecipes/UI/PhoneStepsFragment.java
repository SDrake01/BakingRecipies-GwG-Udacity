package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.Player.VideoPlayer;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class PhoneStepsFragment extends Fragment implements View.OnClickListener {

    VideoView stepVideo;
    TextView stepDescription;
    RecipeViewModel phoneViewModel;
    VideoPlayer phonePlayer;
    public Button btnBack;
    public Button btnNext;
    int thisStep = RecipeViewModel.getListPosition();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.phone_step_layout, container, false);

        // Still need to setup the video player stuff
        stepDescription = view.findViewById(R.id.tv_phone_step_description);
        phoneViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        phonePlayer = new VideoPlayer(getActivity(), view.findViewById(R.id.ep_phone_step_video));
        btnBack = view.findViewById(R.id.btn_instruction_prev);
        btnBack.setOnClickListener(this);
        btnNext = view.findViewById(R.id.btn_instruction_next);
        btnNext.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        // Need to add video player code to this observer
        //phoneViewModel.getSelectedStep().observe(this, stepObject -> stepDescription.setText(stepObject.getDescription()));
        final Observer<StepObject> phoneStepObserver = new Observer<StepObject>() {
            @Override
            public void onChanged(@Nullable StepObject stepObject) {
                stepDescription.setText(stepObject.getDescription());
                // setup ExoPlayer video if there is one
                if (!stepObject.getVideoUrl().isEmpty()){
                    phonePlayer.stopPlayer();
                    phonePlayer.setExoPlayer(stepObject.getVideoUrl());
                } else {
                    phonePlayer.stopPlayer();
                }
            }
        };
        phoneViewModel.getSelectedStep().observe(this, phoneStepObserver);
        limitButtons(thisStep);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_instruction_prev:
                phoneViewModel.updateSelectedStep(thisStep - 1);
                thisStep = RecipeViewModel.getListPosition();
                limitButtons(thisStep);
                break;
            case R.id.btn_instruction_next:
                phoneViewModel.updateSelectedStep(thisStep + 1);
                thisStep = RecipeViewModel.getListPosition();
                limitButtons(thisStep);
                break;
        }
    }

    // This method will remove the prev step button at step 0
    // and remove the next step button at the last step
    void limitButtons(int current){
        if (current < 1){
            btnBack.setVisibility(View.GONE);
        } else if (current == RecipeViewModel.getListLength()-1){
            btnNext.setVisibility(View.GONE);
        }else {
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }
}
