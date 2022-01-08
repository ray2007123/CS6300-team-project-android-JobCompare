package edu.gatech.seclass.jobcompare6300.util;

import static edu.gatech.seclass.jobcompare6300.util.IntUtil.*;

import org.junit.Assert;
import org.junit.Test;

public class IntUtilTest {
    String input;
    boolean output;

    @Test
    public void IntUtilTest_withNull(){
        input = null;
        boolean exceptionCaught = false;
        try {
            output = isGreaterThanIntMaxValue(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should not throw exception " +
                "when called with null string", exceptionCaught);
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should return false when " +
                "called with null string", output);
    }

    @Test
    public void MoneyTest_withEmpty(){
        input = "";
        boolean exceptionCaught = false;
        try {
            output = isGreaterThanIntMaxValue(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should not throw exception " +
                "when called with an empty string", exceptionCaught);
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should return false when " +
                "called with empty string", output);
    }

    @Test
    public void MoneyTest_withInvalid(){
        input = "invalid";
        boolean exceptionCaught = false;
        try {
            output = isGreaterThanIntMaxValue(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should not throw exception " +
                "when called with invalid string", exceptionCaught);
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should return false when " +
                "called with invalid string", output);
    }

    @Test
    public void MoneyTest_withValid_0(){
        input = "1";
        boolean exceptionCaught = false;
        try {
            output = isGreaterThanIntMaxValue(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should not throw exception " +
                "when called with valid string", exceptionCaught);
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should return false when " +
                "called with string '1'", output);
    }

    @Test
    public void MoneyTest_withValid_1(){
        input = "" + Integer.MAX_VALUE + "0";
        boolean exceptionCaught = false;
        try {
            output = isGreaterThanIntMaxValue(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("IntUtil.isGreaterThanIntMaxValue should not throw exception " +
                "when called with valid string", exceptionCaught);
        Assert.assertTrue("IntUtil.isGreaterThanIntMaxValue should return true when " +
                "called with string equivalent to Integer.MAX_VALUE * 10", output);
    }
}
