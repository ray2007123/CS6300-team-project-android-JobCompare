package edu.gatech.seclass.jobcompare6300.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;

import edu.gatech.seclass.jobcompare6300.R;

@RunWith(AndroidJUnit4.class)
public class AdjustComparisonSettingsActivityTest {

    @Rule
    public ActivityTestRule<AdjustComparisonSettingsActivity> tActivityRule = new ActivityTestRule<>(
            AdjustComparisonSettingsActivity.class);

    @Test
    public void testSaveComparisonSettings_0() {
        onView(withId(R.id.annualSalaryWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.annualBonusWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.teleworkWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.leaveTimeWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.gymAllowanceWeight)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveAdjustComparisonButton)).perform(click());
        onView(withId(R.id.annualSalaryWeight)).check(matches(hasErrorText("Invalid annual salary weight")));
        onView(withId(R.id.annualBonusWeight)).check(matches(hasErrorText("Invalid annual bonus weight")));
        onView(withId(R.id.teleworkWeight)).check(matches(hasErrorText("Invalid telework weight")));
        onView(withId(R.id.leaveTimeWeight)).check(matches(hasErrorText("Invalid leave time weight")));
        onView(withId(R.id.gymAllowanceWeight)).check(matches(hasErrorText("Invalid gym membership weight")));
    }

    @Test
    public void testSaveComparisonSettings_1(){
        onView(withId(R.id.annualSalaryWeight)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.annualBonusWeight)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.teleworkWeight)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.leaveTimeWeight)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.gymAllowanceWeight)).perform(clearText(), replaceText("x"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveAdjustComparisonButton)).perform(click());
        onView(withId(R.id.annualSalaryWeight)).check(matches(hasErrorText("Invalid annual salary weight")));
        onView(withId(R.id.annualBonusWeight)).check(matches(hasErrorText("Invalid annual bonus weight")));
        onView(withId(R.id.teleworkWeight)).check(matches(hasErrorText("Invalid telework weight")));
        onView(withId(R.id.leaveTimeWeight)).check(matches(hasErrorText("Invalid leave time weight")));
        onView(withId(R.id.gymAllowanceWeight)).check(matches(hasErrorText("Invalid gym membership weight")));
    }
    @Test
    public void testSaveComparisonSettings_2(){
        BigInteger bigInteger = new BigInteger(""+Integer.MAX_VALUE);
        bigInteger = bigInteger.add(BigInteger.ONE);
        onView(withId(R.id.annualSalaryWeight)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.annualBonusWeight)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.teleworkWeight)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.leaveTimeWeight)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.gymAllowanceWeight)).perform(clearText(), replaceText(bigInteger.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveAdjustComparisonButton)).perform(click());
        onView(withId(R.id.annualSalaryWeight)).check(matches(hasErrorText("Annual salary weight too high")));
        onView(withId(R.id.annualBonusWeight)).check(matches(hasErrorText("Annual bonus weight too high")));
        onView(withId(R.id.teleworkWeight)).check(matches(hasErrorText("Telework weight too high")));
        onView(withId(R.id.leaveTimeWeight)).check(matches(hasErrorText("Leave time weight too high")));
        onView(withId(R.id.gymAllowanceWeight)).check(matches(hasErrorText("Gym membership weight too high")));
    }

}
