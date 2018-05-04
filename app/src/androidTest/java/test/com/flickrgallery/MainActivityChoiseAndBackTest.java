package test.com.flickrgallery;

import android.support.test.espresso.Espresso;
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
public class MainActivityChoiseAndBackTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void RegisterIdlingResource() {
        idlingResource = mainActivityActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void idlingResourceTest() {
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(49, ViewActions.click()));
        //onView(withId(R.id.detail_image)).check(matches(isDisplayed()));
        //onView(withId(R.id.detail_image)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(48, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(47, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(46, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(45, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(4, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(44, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(43, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(6, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(42, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(41, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(8, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(40, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(9, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(39, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(10, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(38, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(11, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(37, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(12, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(36, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(13, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(35, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(14, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(34, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(15, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(33, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(16, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(32, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(17, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(31, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(18, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(30, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(19, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(29, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(20, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(28, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(21, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(27, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(22, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(26, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(23, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(25, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(24, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
    }

    // Не забудьте отменить регистрацию ресурсов, если это не необходимо, чтобы избежать сбоев.
    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
