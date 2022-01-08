package edu.gatech.seclass.jobcompare6300.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JobTest {

    private Job job;
    private ComparisonSettings comparisonSettings;

    @Before
    public void setUp(){
        job = new Job();
    }

    @After
    public void tearDown(){
        job = null;
    }

    @Test
    public void testFromMap_withNull() {
        boolean exceptionCaught = false;
        Map<String, String> map = null;
        try {
            job = job.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                job);
    }

    @Test
    public void testFromMap_withEmptyMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        try {
            job = job.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with an empty map", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                job);
    }

    @Test
    public void testFromMap_withNonEmptyMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        map.put("","");
        try {
            job = job.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with a non-empty map", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                job);
    }

    @Test
    public void testToMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = job.toMap();
        try {
            job = job.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with a non-empty map", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                job);
    }

    @Test
    public void testGetJobScore_withNullComparisonSettings() {
        //Test withNullComparisonSettings
        boolean exceptionCaught = false;
        try {
            job.getJobScore(null);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("getJobScore should not throw exception when passed a null",
                exceptionCaught);
    }

    @Test
    public void testGetJobScore_withNonNullComparisonSettings() {
        //Test withNonNullComparisonSettings
        comparisonSettings = new ComparisonSettings();
        boolean exceptionCaught = false;
        try {
            job.getJobScore(comparisonSettings);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("getJobScore should not throw exception when passed " +
                "a comparisonSettings", exceptionCaught);
    }


    @Test
    public void testTestToString() {
        //Verify returns non null, non empty string
        String string_val = null;
        boolean exceptionCaught = false;
        try {
            string_val = job.toString();
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("toString should not throw exception", exceptionCaught);
        Assert.assertNotNull("toString should return a non-null, non-empty string",
                string_val);
    }
}