package edu.gatech.seclass.jobcompare6300.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ComparisonSettings {
//    private static final String TAG = ComparisonSettings.class.getName();
    public int yearlySalaryWeight = 1;
    public int yearlyBonusWeight = 1;
    public int weeklyTeleworkDaysWeight = 1;
    public int leaveTimeWeight = 1;
    public int gymMembershipAllowanceWeight = 1;

    public ComparisonSettings fromMap(Map<String, String> map){
        if(map == null){
//            Log.w(TAG, "Null map");
            return this;
        }
        if(map.get("yearlySalaryWeight") != null) {
            yearlySalaryWeight = Integer.parseInt(Objects.requireNonNull(map.get("yearlySalaryWeight")));
        }
        if(map.get("yearlyBonusWeight") != null) {
            yearlyBonusWeight = Integer.parseInt(Objects.requireNonNull(map.get("yearlyBonusWeight")));
        }
        if(map.get("weeklyTeleworkDaysWeight") != null) {
            weeklyTeleworkDaysWeight = Integer.parseInt(Objects.requireNonNull(map.get("weeklyTeleworkDaysWeight")));
        }
        if(map.get("leaveTimeWeight") != null) {
            leaveTimeWeight = Integer.parseInt(Objects.requireNonNull(map.get("leaveTimeWeight")));
        }
        if(map.get("gymMembershipAllowanceWeight") != null) {
            gymMembershipAllowanceWeight = Integer.parseInt(Objects.requireNonNull(map.get("gymMembershipAllowanceWeight")));
        }
        return this;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("yearlySalaryWeight", "" + yearlySalaryWeight);
        map.put("yearlyBonusWeight", "" + yearlyBonusWeight);
        map.put("weeklyTeleworkDaysWeight", "" + weeklyTeleworkDaysWeight);
        map.put("leaveTimeWeight", "" + leaveTimeWeight);
        map.put("gymMembershipAllowanceWeight", "" + gymMembershipAllowanceWeight);

        return map;
    }
}
