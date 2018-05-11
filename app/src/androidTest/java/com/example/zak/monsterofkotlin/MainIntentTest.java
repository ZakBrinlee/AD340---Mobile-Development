package com.example.zak.monsterofkotlin;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainIntentTest {

    private static final String MESSAGE = "This is a test";
    private static final String PACKAGE_NAME = "com.example.zak.monsterofkotlin";

    /* using this developer tutorial https://developer.android.com/training/testing/ui-testing/espresso-testing */
    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void verifyMessageSentToMessageActivity() {

        onView(withId(R.id.editText)).perform(typeText(MESSAGE), closeSoftKeyboard());

        onView(withId(R.id.intentButton)).perform(click());

        intended(allOf(hasComponent(hasShortClassName(".IntentActivity")), toPackage(PACKAGE_NAME)));

    }
}
