package stevendrake.bakingrecipes;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import stevendrake.bakingrecipes.UI.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    // I got a lot of help with my testing from @Tane Tachyon
    // I have tried to not reuse her code, but to follow her strategy.
    // I left her code at the end and commented it out to avoid plagiarism

    private static final Intent MAIN_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        mainActivityActivityTestRule.launchActivity(MAIN_ACTIVITY_INTENT);
    }

    @Test
    public void cheesecake_is_three() throws InterruptedException {

        // pause the test to allow the api call and parse the json data
        Thread.sleep(5000);
        // check that the fourth item in the recyclerview is 'Cheesecake'
        onView(withId(R.id.rv_main_fragment_recycler)).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withText("Cheesecake")).check(matches(isDisplayed()));
    }

    @Test
    public void ingredients_collapse_with_title_click() throws InterruptedException {

        // pause the test to allow the api call and parse the json data
        Thread.sleep(5000);
        // click on the first item in the main fragment recycler view
        onView(withId(R.id.rv_main_fragment_recycler)).perform(actionOnItemAtPosition(0, click()));
        // check that the ingredients recycler view is visible
        onView(withId(R.id.rv_ingredients_card_recycler)).check(matches(isDisplayed()));
        // click on the ingredients title to collapse the ingredients recycler view
        onView(withId(R.id.tv_ingredients_card_title)).perform(click());
        // check that the recycler view is not displayed
        onView(withId(R.id.rv_ingredients_card_recycler)).check(matches(not(isDisplayed())));
    }

    @Test
    public void prev_button_not_displayed_on_first_step() throws InterruptedException{

        // pause the test to allow the api call and parse the json data
        Thread.sleep(5000);
        // click on the first item in the main fragment recycler view
        onView(withId(R.id.rv_main_fragment_recycler)).perform(actionOnItemAtPosition(0,click()));
        // check that the instructions recycler view is visible
        onView(withId(R.id.rv_steps_card_recycler)).check(matches(isDisplayed()));
        // click on the second item in the steps card recycler view
        onView(withId(R.id.rv_steps_card_recycler)).perform(actionOnItemAtPosition(1, click()));
        // check that the prev step button is displayed
        onView(withId(R.id.btn_instruction_prev)).check(matches(isDisplayed()));
        // click on the prev step button
        onView(withId(R.id.btn_instruction_prev)).perform(click());
        // check that the prev step button is NOT displayed
        onView(withId(R.id.btn_instruction_prev)).check(matches(not(isDisplayed())));
    }


}
// * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// The following code is from @Tane Tachyon
// * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//public class SelectRecipeAndStepsTest {
//
//    public static final String BROWNIES = "Brownies";
//    public static final String STEP_1 = "Step 1";
//    public static final String STEP_2 = "Step 2";
//    public static final String RECIPE_INTRODUCTION = "Recipe Introduction";
//
//    @Rule
//    public ActivityTestRule<RecipeListActivity> mActivityTestRule
//            = new ActivityTestRule<>(RecipeListActivity.class);
//
//    @Test
//    public void browniesSecondInRecipeList() {
//        // When the app loads, is Brownies the second recipe?
//        onView(withId(R.id.rv_recipe_cards))
//                .perform(RecyclerViewActions
//                        .scrollToPosition(1));
//
//        onView(withText(BROWNIES)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void selectRecipeAndSteps() {
//        // If you tap the Brownies recipe card in RecipeListActivity ...
//        onView(withId(R.id.rv_recipe_cards))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(1, click()));
//
//        // ... does it bring up RecipeDetailActivity, with Brownies as the selected recipe?
//        onView(withId(R.id.tv_recipe_detail_name)).check(matches(withText(BROWNIES)));
//
//        // And if you then tap Step 1 in the list of steps (which is actually second in the list
//        // of steps, following Recipe Introduction ...
//        onView(withId(R.id.rv_recipe_steps))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition(1, click()));
//
//        // ... does it bring up the instructions for Step 1, either in RecipeStepActivity (phone)
//        // or in RecipeStepFragment in RecipeDetailActivity (tablet)?
//        onView(withId(R.id.tv_step_description)).check(matches(withText(startsWith(STEP_1))));
//
//        // And if you then tap the Next button ...
//        onView(withId(R.id.btn_next_step)).perform(click());
//
//        // ... does it advance to step 2?
//        onView(withId(R.id.tv_step_description)).check(matches(withText(startsWith(STEP_2))));
//
//        // And if you then tap the Previous button ...
//        onView(withId(R.id.btn_previous_step)).perform(click());
//
//        // ... does it return to step 1?
//        onView(withId(R.id.tv_step_description)).check(matches(withText(startsWith(STEP_1))));
//
//        // And if you then tap the Previous button a second time ...
//        onView(withId(R.id.btn_previous_step)).perform(click());
//
//        // ... does it go to the Recipe Introduction step?
//        onView(withId(R.id.tv_step_description)).check(matches(withText(RECIPE_INTRODUCTION)));
//
//        // And when you're at the Recipe Introduction step, is the Previous button no longer enabled?
//        onView(withId(R.id.btn_previous_step)).check(matches(not(isEnabled())));
//    }
//
//}