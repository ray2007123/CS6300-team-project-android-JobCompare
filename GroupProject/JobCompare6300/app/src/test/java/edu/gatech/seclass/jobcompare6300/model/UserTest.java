package edu.gatech.seclass.jobcompare6300.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    private ComparisonSettings comparisonSettings;

    @Test
    public void testGetComparisonSettings(){
        boolean exceptionCaught = false;
        try {
            comparisonSettings = User.getComparisonSettings();
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("getComparisonSettings should not throw exception", exceptionCaught);
        Assert.assertNotNull("getComparisonSettings should return a comparisonSettings object",
                comparisonSettings);
    }

    @Test
    public void testSetComparisonSettings_withNull(){
        boolean exceptionCaught = false;
        try {
            User.setComparisonSettings(null);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("setComparisonSettings should not throw exception when passed a null",
                exceptionCaught);
    }

    @Test
    public void testSetComparisonSettings_withNonNull(){
        comparisonSettings = new ComparisonSettings();
        boolean exceptionCaught = false;
        try {
            User.setComparisonSettings(comparisonSettings);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("setComparisonSettings should not throw exception when passed " +
                        "a comparisonSettings", exceptionCaught);
    }

}
