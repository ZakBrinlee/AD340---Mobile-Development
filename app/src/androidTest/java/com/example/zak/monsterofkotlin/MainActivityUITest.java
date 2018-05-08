package com.example.zak.monsterofkotlin;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ET_BTN_Exists() throws Exception {
        //check editText
        onView(withId(R.id.editText)).check(matches(isDisplayed()));

        //check submit button
        onView(withId(R.id.intentButton)).check(matches(isDisplayed()));
    }//end of editTextExists

    @Test
    public void validate_Text_Entry(){
        //Type text
        onView(withId(R.id.editText)).perform(typeText("Test this line"), closeSoftKeyboard());

        //click button for intent
        onView(withId(R.id.intentButton)).perform(click());
    }//end of validate text entry

}
