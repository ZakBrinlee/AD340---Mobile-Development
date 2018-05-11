package com.example.zak.monsterofkotlin;

import android.content.SharedPreferences;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.zak.monsterofkotlin.SharedPrefsHelper;

@RunWith(MockitoJUnitRunner.class)
public class SharedPrefsTest {

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor ;


    private SharedPrefsHelper mMockSharedPreferencesHelper;

    private String test_String = "This is my SharedPrefs test";

    @Before
    public void initMocks() {

        // Create a mocked SharedPreferences.
        mMockSharedPreferencesHelper = createMockSharedPreference();

    }

    @Test
    public void sharedPreferences_SaveAndReadEntry() {

        // Save the personal information to SharedPreferences
        boolean success = mMockSharedPreferencesHelper.putSharedPrefsHelper(test_String);

        assertThat("SharedPreferenceEntry.save... returns true",
                success, is(true));

        assertEquals(test_String, mMockSharedPreferencesHelper.getSharedPrefs());

    }

    /**
     * Creates a mocked SharedPreferences object for successful read/write
     */
    private SharedPrefsHelper createMockSharedPreference() {

        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        when(mMockSharedPreferences.getString(eq("text_entry"), anyString()))
                .thenReturn(test_String);

        // Mocking a successful commit.
        when(mMockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);

        return new SharedPrefsHelper(mMockSharedPreferences);
    }
}
