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
public class EnterJobOfferActivityTest {
    @Rule
    public ActivityTestRule<EnterJobOfferActivity> tActivityRule = new ActivityTestRule<>(
            EnterJobOfferActivity.class);

    @Test
    public void testSaveJobOffer_0() {
        onView(withId(R.id.jobOfferTitle)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferCompany)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferLocation)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferCostOfLiving)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferAnnualSalary)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferAnnualBonus)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferLeaveTime)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobOfferButton)).perform(click());
        onView(withId(R.id.jobOfferTitle)).check(matches(hasErrorText("Invalid title")));
        onView(withId(R.id.jobOfferCompany)).check(matches(hasErrorText("Invalid company")));
        onView(withId(R.id.jobOfferLocation)).check(matches(hasErrorText("Invalid location")));
        onView(withId(R.id.jobOfferCostOfLiving)).check(matches(hasErrorText("Invalid cost of living")));
        onView(withId(R.id.jobOfferAnnualSalary)).check(matches(hasErrorText("Invalid annual salary")));
        onView(withId(R.id.jobOfferAnnualBonus)).check(matches(hasErrorText("Invalid annual bonus")));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.jobOfferLeaveTime)).check(matches(hasErrorText("Invalid leave time")));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).check(matches(hasErrorText("Invalid gym membership allowance")));
    }

    @Test
    public void testSaveJobOffer_1() {
        onView(withId(R.id.jobOfferCostOfLiving)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.jobOfferAnnualSalary)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.jobOfferAnnualBonus)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.jobOfferLeaveTime)).perform(clearText(), replaceText("x"));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).perform(clearText(), replaceText("x"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobOfferButton)).perform(click());
        onView(withId(R.id.jobOfferCostOfLiving)).check(matches(hasErrorText("Invalid cost of living")));
        onView(withId(R.id.jobOfferAnnualSalary)).check(matches(hasErrorText("Invalid annual salary")));
        onView(withId(R.id.jobOfferAnnualBonus)).check(matches(hasErrorText("Invalid annual bonus")));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.jobOfferLeaveTime)).check(matches(hasErrorText("Invalid leave time")));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).check(matches(hasErrorText("Invalid gym membership allowance")));
    }

    @Test
    public void testSaveJobOffer_2() {
        BigInteger bigInteger = new BigInteger(""+Integer.MAX_VALUE);
        bigInteger = bigInteger.add(BigInteger.ONE);
        onView(withId(R.id.jobOfferCostOfLiving)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.jobOfferAnnualSalary)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.jobOfferAnnualBonus)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.jobOfferLeaveTime)).perform(clearText(), replaceText(bigInteger.toString()));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).perform(clearText(), replaceText(bigInteger.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobOfferButton)).perform(click());
        onView(withId(R.id.jobOfferCostOfLiving)).check(matches(hasErrorText("Cost of living too high")));
        onView(withId(R.id.jobOfferAnnualSalary)).check(matches(hasErrorText("Annual salary too high")));
        onView(withId(R.id.jobOfferAnnualBonus)).check(matches(hasErrorText("Annual bonus too high")));
        onView(withId(R.id.jobOfferWeeklyTeleworkDays)).check(matches(hasErrorText("Weekly telework days too high")));
        onView(withId(R.id.jobOfferLeaveTime)).check(matches(hasErrorText("Leave time too high")));
        onView(withId(R.id.jobOfferGymMembershipAllowance)).check(matches(hasErrorText("Gym membership allowance too high")));
    }
}
