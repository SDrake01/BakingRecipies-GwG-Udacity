package stevendrake.bakingrecipes.UI;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import stevendrake.bakingrecipes.Data.StepObject;
import stevendrake.bakingrecipes.R;

public class TabletStepsFragment extends Fragment {

    VideoView stepVideo;
    TextView stepNumber;
    TextView stepDescription;

    RecipeFragment recipeFragment = new RecipeFragment();
    StepObject tabletSteps = new StepObject();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.tablet_step_layout, container, false);

        stepNumber = view.findViewById(R.id.tv_tablet_step_title);
        stepDescription = view.findViewById(R.id.tv_tablet_step_description);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){

        stepNumber.setText("Step One");
        stepDescription.setText("do stuff to the stuff so that when you're done the stuff looks like the good stuff");
    }
}
