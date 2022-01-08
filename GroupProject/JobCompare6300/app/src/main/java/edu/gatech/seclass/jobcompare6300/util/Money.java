package edu.gatech.seclass.jobcompare6300.util;

public class Money {
    public int value;
    public Money(String sValue){
        if(sValue == null || sValue.length() == 0){
            value = 0;
            return;
        }
        try {
            value = Integer.parseInt(sValue);
        }catch (NumberFormatException numberFormatException){
            value = 0;
        }
    }
}
