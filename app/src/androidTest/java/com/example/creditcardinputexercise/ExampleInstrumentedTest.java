package com.example.creditcardinputexercise;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.ContentView;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String cardNameToBeTyped;
    private String mmyyNameToBeTyped;
    private String securityCodeToBeTyped;
    private String firstNameToBeTyped;
    private String lastNameToBeTyped;

    private String cardNameToBeTypedIn;
    private String mmyyNameToBeTypedIn;
    private String securityCodeToBeTypedIn;
    private String firstNameToBeTypedIn;
    private String lastNameToBeTypedIn;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.creditcardinputexercise", appContext.getPackageName());
    }

    @Before
    public void initValidString() {
        // Specify a valid string.
        cardNameToBeTyped = "371449635398431";
        mmyyNameToBeTyped = "08/21";
        securityCodeToBeTyped = "5678";
        firstNameToBeTyped = "Jo";
        lastNameToBeTyped = "mo";
    }

    @Before
    public void initInValidString() {
        // Specify a valid string.
        cardNameToBeTypedIn = "371449635398436";
        mmyyNameToBeTypedIn = "08/58";
        securityCodeToBeTypedIn = "567";
        firstNameToBeTypedIn = "5646456456456";
        lastNameToBeTypedIn = "";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(pressBack());
    }

    @Test
    public void check_when_good_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText("4111111111111111"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText("10/20"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText("626"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText("Jane"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        onView(withText("OK")).inRoot(isDialog()).check(matches(isDisplayed())).perform(pressBack());
    }

    @Test
    public void check_when_card_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        //onView(ViewMatchers.withId(R.id.card_number)).check(matches(withText("Invalid number")));
        onView(ViewMatchers.withId(R.id.card_number)).check(matches(hasTextInputLayoutErrorText("Invalid number")));
    }

    @Test
    public void check_when_mmyy_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        //onView(ViewMatchers.withId(R.id.card_number)).check(matches(withText("Invalid number")));
        onView(ViewMatchers.withId(R.id.card_number)).check(matches(hasTextInputLayoutErrorText("Card Expired")));
    }

    @Test
    public void check_when_security_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        //onView(ViewMatchers.withId(R.id.card_number)).check(matches(withText("Invalid number")));
        onView(ViewMatchers.withId(R.id.card_number)).check(matches(hasTextInputLayoutErrorText("Invalid length")));
    }

    @Test
    public void check_when_firstn_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        //onView(ViewMatchers.withId(R.id.card_number)).check(matches(withText("Invalid number")));
        onView(ViewMatchers.withId(R.id.card_number)).check(matches(hasTextInputLayoutErrorText("Invalid name")));
    }

    @Test
    public void check_when_ln_error() {
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(typeText(cardNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(typeText(mmyyNameToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(typeText(securityCodeToBeTyped), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(typeText(firstNameToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(typeText(lastNameToBeTypedIn), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.submit)).perform(click());
        //onView(ViewMatchers.withId(R.id.card_number)).check(matches(withText("Invalid number")));
        onView(ViewMatchers.withId(R.id.card_number)).check(matches(hasTextInputLayoutErrorText("Field Empty")));
    }

}