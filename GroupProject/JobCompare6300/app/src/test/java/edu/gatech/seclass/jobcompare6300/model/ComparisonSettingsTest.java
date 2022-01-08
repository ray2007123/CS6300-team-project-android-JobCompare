package edu.gatech.seclass.jobcompare6300.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ComparisonSettingsTest {

    private ComparisonSettings comparisonSettings;

    @Before
    public void setUp(){
        comparisonSettings = new ComparisonSettings();
    }

    @After
    public void tearDown(){
        comparisonSettings = null;
    }

    @Test
    public void testFromMap_withNull() {
        boolean exceptionCaught = false;
        Map<String, String> map = null;
        try {
            comparisonSettings = comparisonSettings.fromMap(map);
        }catch (Exception exception){
            //Fails because:
            //java.lang.RuntimeException: Method w in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.
            //Note 1:
            // above link gives this result:
            //Invalid Dynamic Link - Blocked
            //Note 2:
            //This page: http://tools.android.com/tech-docs/unit-testing-support
            //gives this result:
            //This page is obsolete and not maintained.
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                comparisonSettings);

    }

    @Test
    public void testFromMap_withEmptyMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        try {
            comparisonSettings = comparisonSettings.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                comparisonSettings);
    }

    @Test
    public void testFromMap_withNonEmptyMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        map.put("","");
        try {
            comparisonSettings = comparisonSettings.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                comparisonSettings);
    }

    @Test
    public void testTomMap() {
        boolean exceptionCaught = false;
        Map<String, String> map = comparisonSettings.toMap();

        try {
            comparisonSettings = comparisonSettings.fromMap(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("fromMap should not throw exception " +
                "when called with null view", exceptionCaught);
        Assert.assertNotNull("fromMap should return not null when called with null view",
                comparisonSettings);
    }
}