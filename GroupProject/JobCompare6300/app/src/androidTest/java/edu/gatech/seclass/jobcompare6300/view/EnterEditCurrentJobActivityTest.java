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
public class EnterEditCurrentJobActivityTest {

    @Rule
    public ActivityTestRule<EnterEditCurrentJobActivity> tActivityRule = new ActivityTestRule<>(
            EnterEditCurrentJobActivity.class);

    @Test
    public void testSaveCurrentJob_0() {
        onView(withId(R.id.currentJobTitle)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobCompany)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobLocation)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobCostOfLiving)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobAnnualSalary)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobAnnualBonus)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobLeaveTime)).perform(clearText(), replaceText(""));
        onView(withId(R.id.currentJobGymMembershipAllowance)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButton)).perform(click());
        onView(withId(R.id.currentJobTitle)).check(matches(hasErrorText("Invalid title")));
        onView(withId(R.id.currentJobCompany)).check(matches(hasErrorText("Invalid company")));
        onView(withId(R.id.currentJobLocation)).check(matches(hasErrorText("Invalid location")));
        onView(withId(R.id.currentJobCostOfLiving)).check(matches(hasErrorText("Invalid cost of living")));
        onView(withId(R.id.currentJobAnnualSalary)).check(matches(hasErrorText("Invalid annual salary")));
        onView(withId(R.id.currentJobAnnualBonus)).check(matches(hasErrorText("Invalid annual bonus")));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.currentJobLeaveTime)).check(matches(hasErrorText("Invalid leave time")));
        onView(withId(R.id.currentJobGymMembershipAllowance)).check(matches(hasErrorText("Invalid gym membership allowance")));
    }

    @Test
    public void testSaveCurrentJob_1() {
        onView(withId(R.id.currentJobCostOfLiving)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.currentJobAnnualSalary)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.currentJobAnnualBonus)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.currentJobLeaveTime)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.currentJobGymMembershipAllowance)).perform(clearText(), replaceText("x"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButton)).perform(click());
        onView(withId(R.id.currentJobCostOfLiving)).check(matches(hasErrorText("Invalid cost of living")));
        onView(withId(R.id.currentJobAnnualSalary)).check(matches(hasErrorText("Invalid annual salary")));
        onView(withId(R.id.currentJobAnnualBonus)).check(matches(hasErrorText("Invalid annual bonus")));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.currentJobLeaveTime)).check(matches(hasErrorText("Invalid leave time")));
        onView(withId(R.id.currentJobGymMembershipAllowance)).check(matches(hasErrorText("Invalid gym membership allowance")));
    }

    @Test
    public void testSaveCurrentJob_2() {
        BigInteger bigInteger = new BigInteger(""+Integer.MAX_VALUE);
        bigInteger = bigInteger.add(BigInteger.ONE);
        onView(withId(R.id.currentJobCostOfLiving)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.currentJobAnnualSalary)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.currentJobAnnualBonus)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.currentJobLeaveTime)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.currentJobGymMembershipAllowance)).perform(clearText(), replaceText(bigInteger.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveCurrentJobButton)).perform(click());
        onView(withId(R.id.currentJobCostOfLiving)).check(matches(hasErrorText("Cost of living too high")));
        onView(withId(R.id.currentJobAnnualSalary)).check(matches(hasErrorText("Annual salary too high")));
        onView(withId(R.id.currentJobAnnualBonus)).check(matches(hasErrorText("Annual bonus too high")));
        onView(withId(R.id.currentJobWeeklyTeleworkDays)).check(matches(hasErrorText("Weekly telework days too high")));
        onView(withId(R.id.currentJobLeaveTime)).check(matches(hasErrorText("Leave time too high")));
        onView(withId(R.id.currentJobGymMembershipAllowance)).check(matches(hasErrorText("Gym membership allowance too high")));
    }

}
