package com.example.zak.monsterofkotlin;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SharedPrefTests {

    private static final String TEST_MESSAGE = "This is a test message";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;

    @Test
    public void testSharedPrefs(){
        SharedPrefsHelper sharedPrefs = new SharedPrefsHelper(mActivity.context);
        sharedPrefs.putSharedPrefsHelper(TEST_MESSAGE);
        String returnedString = sharedPrefs.getSharedPrefs();
        assertEquals(TEST_MESSAGE, returnedString);
    }

}
