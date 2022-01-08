package edu.gatech.seclass.jobcompare6300.controller;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.view.EnterEditCurrentJobActivity;

@RunWith(AndroidJUnit4.class)
public class JobManagerTest {
    @Rule
    public ActivityTestRule<JobManager> tActivityRule = new ActivityTestRule<>(
            JobManager.class);

    @Test
    public void enterCurrentJobDetails_test() {
        boolean exceptionCaught = false;
        try {
            onView(withId(R.id.currentJobButton)).perform(click());
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("enterCurrentJobDetails should not throw exception",
                exceptionCaught);
    }

    @Test
    public void enterJobOfferDetails_test() {
        boolean exceptionCaught = false;
        try {
            onView(withId(R.id.jobOfferButton)).perform(click());
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("enterJobOfferDetails should not throw exception",
                exceptionCaught);
    }

    @Test
    public void adjustComparisonSettings_test() {
        boolean exceptionCaught = false;
        try {
            onView(withId(R.id.adjustComparisonSettingsButton)).perform(click());
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("adjustComparisonSettings should not throw exception",
                exceptionCaught);
    }

    @Test
    public void compareJobs_test() {
        boolean exceptionCaught = false;
        try {
            onView(withId(R.id.compareJobsButton)).perform(click());
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("compareJobs should not throw exception", exceptionCaught);
    }
}
