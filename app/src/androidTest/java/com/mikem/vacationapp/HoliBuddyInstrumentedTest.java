package com.mikem.vacationapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * This test is designed to verify that the application context is correctly initialized within the app.
 * It retrieves the application context using InstrumentationRegistry and asserts that the package name
 * is the expected one. This is a fundamental test to ensure that the app's basic environment is set up correctly.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HoliBuddyInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Asserts that the retrieved context's package name is correct.
        assertEquals("com.mikem.vacationapp", appContext.getPackageName());
    }
}
