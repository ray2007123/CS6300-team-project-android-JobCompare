package edu.gatech.seclass.jobcompare6300.model;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CurrentJobTest {

    private CurrentJob currentJob;

    @Before
    public void setUp(){
        currentJob = new CurrentJob();
    }

    @After
    public void tearDown(){
        currentJob = null;
    }

    @Test
    public void testFromMap_withNull() {
        //See ComparisonSettingsTest for example of what goes here
        boolean exceptionCaught = false;
        Map<String, String> map = null;
        try {
            currentJob = currentJob.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                currentJob);
    }

    @Test
    public void testFromMap_withEmptyMap() {
        //See ComparisonSettingsTest for example of what goes here
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        try {
            currentJob = currentJob.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                currentJob);
    }

    @Test
    public void testFromMap_withNonEmptyMap() {
        //See ComparisonSettingsTest for example of what goes here
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        map.put("","");

        try {
            currentJob = currentJob.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                currentJob);
    }

}