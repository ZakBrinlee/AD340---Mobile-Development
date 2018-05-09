package com.example.zak.monsterofkotlin

import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.AllOf.allOf

@RunWith(AndroidJUnit4::class)
class MainActivityIntentTest{

    private val MESSAGE = "This is a test"
    private val PACKAGE_NAME = "com.example.zak.monsterofkotlin"

    /* Instantiate an IntentsTestRule object. */
    @Rule
    public var mIntentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun verifyMessageSentToIntentActivity() {

        // Types a message into a EditText element.
        onView(withId(R.id.editText))
                .perform(typeText(MESSAGE), closeSoftKeyboard())

        // Clicks a button to send the message to another
        // activity through an explicit intent.
        onView(withId(R.id.intentButton)).perform(click())

        // Verifies that the DisplayMessageActivity received an intent
        // with the correct package name and message.
        intended(allOf(
                hasComponent(hasShortClassName(".IntentActivity")),
                toPackage(PACKAGE_NAME)))
    }

}

