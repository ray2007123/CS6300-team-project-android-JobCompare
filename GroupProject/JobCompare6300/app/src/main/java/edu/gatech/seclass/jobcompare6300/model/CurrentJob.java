package edu.gatech.seclass.jobcompare6300.model;

import android.util.Log;

import java.util.Map;
import java.util.Objects;

import edu.gatech.seclass.jobcompare6300.util.Money;

public class CurrentJob extends Job {

//    private static final String TAG = CurrentJob.class.getName();

    public CurrentJob() {
        isCurrent = true;
    }

    public CurrentJob fromMap(Map<String, String> map) {
        if(map == null){
//            Log.w(TAG, "Null map");
            return this;
        }
        if (map.get("jobId") != null) {
            id = Integer.parseInt(Objects.requireNonNull(map.get("jobId")));
        } else {
            id = 1;
        }
        title = map.get("title");
        company = map.get("company");
        location = map.get("location");
        if (map.get("costOfLiving") != null) {
            costOfLiving = Integer.parseInt(Objects.requireNonNull(map.get("costOfLiving")));
        } else {
            costOfLiving = 0;
        }
        yearlySalary = new Money(map.get("yearlySalary"));
        yearlyBonus = new Money(map.get("yearlyBonus"));
        if (map.get("weeklyTeleworkDays") != null) {
            weeklyTeleworkDays = Integer.parseInt(Objects.requireNonNull(map.get("weeklyTeleworkDays")));
        } else {
            weeklyTeleworkDays = 0;
        }
        if (map.get("leaveTime") != null) {
            leaveTime = Integer.parseInt(Objects.requireNonNull(map.get("leaveTime")));
        } else {
            leaveTime = 0;
        }
        gymMembershipAllowance = new Money(map.get("gymMembershipAllowance"));
        isCurrent = true;
        return this;
    }
}
