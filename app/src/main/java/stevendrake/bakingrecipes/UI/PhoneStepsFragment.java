package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
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

public class PhoneStepsFragment extends Fragment implements View.OnClickListener {

    VideoView stepVideo;
    TextView stepDescription;
    RecipeViewModel phoneViewModel;
    public Button btnBack;
    public Button btnNext;
    int thisStep = RecipeViewModel.getListPosition();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.phone_step_layout, container, false);

        // Still need to setup the video player stuff
        stepDescription = view.findViewById(R.id.tv_phone_step_description);
        phoneViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        btnBack = view.findViewById(R.id.btn_instruction_prev);
        btnBack.setOnClickListener(this);
        btnNext = view.findViewById(R.id.btn_instruction_next);
        btnNext.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        // Need to add video player code to this observer
        phoneViewModel.getSelectedStep().observe(this, stepObject -> stepDescription.setText(stepObject.getDescription()));
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

    public void onPause(){
        // This is to reset the view model selected step to the beginning if the user
        // navigates back to the recipe list using the back button, but not if the user
        // rotates the screen to the landscape view on a tablet
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            phoneViewModel.updateSelectedStep(0);
        }
        super.onPause();
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
