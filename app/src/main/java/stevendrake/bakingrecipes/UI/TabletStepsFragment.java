package stevendrake.bakingrecipes.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.Player.VideoPlayer;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class TabletStepsFragment extends Fragment {

    TextView stepDescription;
    RecipeViewModel tabletViewModel;
    View view;
    VideoPlayer tabletPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.tablet_step_layout, container, false);

        stepDescription = view.findViewById(R.id.tv_tablet_step_description);
        tabletViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        tabletPlayer = new VideoPlayer(getActivity(), view.findViewById(R.id.ep_tablet_step_video));

        final Observer<StepObject> tabletStepObserver = new Observer<StepObject>() {
            @Override
            public void onChanged(@Nullable StepObject stepObject) {
                // Update the description for the selected recipe step
                stepDescription.setText(stepObject.getDescription());
                // Stop the current player (if there is one), and start
                // the current player if this step has a video
                if (!stepObject.getVideoUrl().isEmpty()){
                    tabletPlayer.unHidePlayer();
                    startPlayer(tabletPlayer, stepObject.getVideoUrl());
                } else if (!stepObject.getThumbUrl().isEmpty()) {
                    tabletPlayer.unHidePlayer();
                    startPlayer(tabletPlayer, stepObject.getThumbUrl());
                } else {
                    tabletPlayer.stopPlayer();
                    tabletPlayer.hidePlayer();
                }
            }
        };
        tabletViewModel.getSelectedStep().observe(this,tabletStepObserver);

        return view;
    }

    public void onPause(){
        super.onPause();
        RecipeViewModel.savePosition(tabletPlayer);
    }

    public void onStop(){
        tabletPlayer.stopPlayer();
        super.onStop();
    }

    void startPlayer(VideoPlayer player, String stepUrl){
        if (RecipeViewModel.setPosition() != null && RecipeViewModel.setPosition() > 1){
            player.stopPlayer();
            player.setExoPlayerPosition(stepUrl, RecipeViewModel.setPosition());
        } else {
            player.stopPlayer();
            player.setExoPlayer(stepUrl);
        }
    }
}
