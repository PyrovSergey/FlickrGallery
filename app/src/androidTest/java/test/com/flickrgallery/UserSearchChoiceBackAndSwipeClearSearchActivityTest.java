package test.com.flickrgallery;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UserSearchChoiceBackAndSwipeClearSearchActivityTest {
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
        onView(withId(R.id.search_button)).perform(click());
        onView(withResourceName("search_src_text"))
                .perform(typeText("cat"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.search_src_text)).check(matches(withText("cat")));
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(15, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        onView(withId(R.id.photo_recycler_view)).perform(swipeDown());
        onView(withId(R.id.search_src_text)).check(matches(withText("")));
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(15, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        onView(withResourceName("search_src_text"))
                .perform(typeText("dog"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.search_src_text)).check(matches(withText("dog")));
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(15, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        onView(withId(R.id.search_close_btn)).perform(click());
        onView(withId(R.id.search_src_text)).check(matches(withText("")));
        onView(withId(R.id.search_close_btn)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(15, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));
        onView(withId(R.id.image_back)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
