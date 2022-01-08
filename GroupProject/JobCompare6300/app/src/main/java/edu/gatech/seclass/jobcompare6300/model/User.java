package edu.gatech.seclass.jobcompare6300.model;

public class User {
    private static ComparisonSettings _comparisonSettings;

    private User(){ }

    public static ComparisonSettings getComparisonSettings(){
        return _comparisonSettings;
    }

    public static void setComparisonSettings(ComparisonSettings comparisonSettings){
        _comparisonSettings = comparisonSettings;
    }

}
