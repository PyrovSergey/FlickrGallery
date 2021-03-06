package test.com.flickrgallery;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityLoadTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void RegisterIdlingResource() {
        idlingResource = mainActivityActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void idlingResourceTest() {
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.scrollToPosition(49));
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(49, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
