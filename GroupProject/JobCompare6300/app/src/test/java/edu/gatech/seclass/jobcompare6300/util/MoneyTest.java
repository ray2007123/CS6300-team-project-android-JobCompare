package edu.gatech.seclass.jobcompare6300.util;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

    private Money money;
    private String input;

    @Test
    public void MoneyTest_withNull(){
        input = null;
        boolean exceptionCaught = false;
        try {
            money = new Money(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("Money constructor should not throw exception " +
                "when called with null string", exceptionCaught);
        Assert.assertEquals("Money should have value = 0 when called with null string",
                0, money.value);
    }

    @Test
    public void MoneyTest_withEmpty(){
        input = "";
        boolean exceptionCaught = false;
        try {
            money = new Money(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("Money constructor should not throw exception " +
                "when called with an empty string", exceptionCaught);
        Assert.assertEquals("Money should have value = 0 when called with empty string",
                0, money.value);
    }

    @Test
    public void MoneyTest_withInvalid(){
        input = "invalid";
        boolean exceptionCaught = false;
        try {
            money = new Money(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("Money constructor should not throw exception " +
                "when called with invalid string", exceptionCaught);
        Assert.assertEquals("Money should have value = 0 when called with invalid string",
                0, money.value);
    }

    @Test
    public void MoneyTest_withValid(){
        input = "1";
        boolean exceptionCaught = false;
        try {
            money = new Money(input);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        Assert.assertFalse("Money constructor should not throw exception " +
                "when called with valid string", exceptionCaught);
        Assert.assertEquals("Money should have value = 1 when called with string '1'",
                1, money.value);
    }

}
