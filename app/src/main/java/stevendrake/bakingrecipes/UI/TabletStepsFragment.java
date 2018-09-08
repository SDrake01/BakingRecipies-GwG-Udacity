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

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.Player.VideoPlayer;
import stevendrake.bakingrecipes.R;
import stevendrake.bakingrecipes.ViewModels.RecipeViewModel;

public class TabletStepsFragment extends Fragment {

    TextView stepDescription;
    RecipeViewModel tabletViewModel;
    View view;
    // ExoPlayer variables
    private PlayerView recipePlayerView;
    private SimpleExoPlayer recipePlayer;
    // This VideoPlayer object is from my exoplayer class and replaces the two above
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
                    tabletPlayer.stopPlayer();
                    tabletPlayer.setExoPlayer(stepObject.getVideoUrl());
                    //releasePlayer(recipePlayer);
                    //initializePlayer(stepObject.getVideoUrl());
                } else {
                    // Add code to show a thumbnail or a placeholder if there is no video URL
                    //releasePlayer(recipePlayer);
                    tabletPlayer.stopPlayer();
                }
            }
        };
        tabletViewModel.getSelectedStep().observe(this,tabletStepObserver);

        return view;
    }

    //
    // Could this be moved to a separate package?
    //
    // This method sets up an ExoPlayer using a passed in URL in String format
//    private void initializePlayer(String newUrl){
//
//        recipePlayerView = view.findViewById(R.id.ep_tablet_step_video);
//        recipePlayerView.requestFocus();
//
//        // Create a basic instance of an ExoPlayerFactory
//        recipePlayer = ExoPlayerFactory.newSimpleInstance(
//                new DefaultRenderersFactory(getActivity()),
//                new DefaultTrackSelector(),
//                new DefaultLoadControl()
//        );
//
//        // Attach the player to the view on the screen
//        recipePlayerView.setPlayer(recipePlayer);
//
//        // This translates the URL string from the JSON to a Uri that ExoPlayer can use
//        // to play the video
//        Uri playerUri = Uri.parse(newUrl);
//        MediaSource playerMediaSource = new ExtractorMediaSource(playerUri,
//                new DefaultDataSourceFactory(getActivity(),"BakingRecipes"),
//                new DefaultExtractorsFactory(), null, null);
//
//        // Prepare the player using the translated media source and set it to play when ready
//        recipePlayer.prepare(playerMediaSource, true, false);
//        recipePlayer.setPlayWhenReady(true);
//    }
//
//    // This method stops any passed in player and releases its resources
//    private void releasePlayer(SimpleExoPlayer player){
//        if (player != null) {
//            player.stop();
//            player.release();
//        }
//    }
}
