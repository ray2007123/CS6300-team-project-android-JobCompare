package edu.gatech.seclass.jobcompare6300.model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.gatech.seclass.jobcompare6300.util.Money;

public class Job {

//    private static final String TAG = Job.class.getName();

    public int id = 0;
    public String title = "";
    public String company = "";
    public String location = "";
    public int costOfLiving = 0;
    public Money yearlySalary = new Money("0");
    public Money yearlyBonus = new Money("0");
    public int weeklyTeleworkDays = 0;
    public int leaveTime = 0;
    public Money gymMembershipAllowance = new Money("0");
    public boolean isCurrent = false;
    public boolean checked = false;
    public double score = 0.0f;

    public Job fromMap(Map<String, String> map) {
        if(map == null){
//            Log.w(TAG, "Null map");
            return this;
        }
        if (map.get("jobId") != null) {
            id = Integer.parseInt(Objects.requireNonNull(map.get("jobId")));
        }
        title = map.get("title");
        company = map.get("company");
        location = map.get("location");
        if (map.get("costOfLiving") != null) {
            costOfLiving = Integer.parseInt(Objects.requireNonNull(map.get("costOfLiving")));
        }
        yearlySalary = new Money(map.get("yearlySalary"));
        yearlyBonus = new Money(map.get("yearlyBonus"));
        if (map.get("weeklyTeleworkDays") != null) {
            weeklyTeleworkDays = Integer.parseInt(Objects.requireNonNull(map.get("weeklyTeleworkDays")));
        }
        if (map.get("leaveTime") != null) {
            leaveTime = Integer.parseInt(Objects.requireNonNull(map.get("leaveTime")));
        }
        gymMembershipAllowance = new Money(map.get("gymMembershipAllowance"));
        if (map.get("isCurrent") != null) {
            isCurrent = Boolean.parseBoolean(Objects.requireNonNull(map.get("isCurrent")));
        }
        return this;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("jobId", "" + id);
        map.put("title", title);
        map.put("company", company);
        map.put("location", location);
        map.put("costOfLiving", "" + costOfLiving);
        map.put("yearlySalary", "" + yearlySalary.value);
        map.put("yearlyBonus", "" + yearlyBonus.value);
        map.put("weeklyTeleworkDays", "" + weeklyTeleworkDays);
        map.put("leaveTime", "" + leaveTime);
        map.put("gymMembershipAllowance", "" + gymMembershipAllowance.value);
        map.put("isCurrent", "" + isCurrent);

        return map;
    }

    public double getJobScore(ComparisonSettings comparisonSettings) {
        if(comparisonSettings == null){
//            Log.w(TAG, "Null comparisonSettings");
            return 0.0f;
        }
        final double AYS = ((double) yearlySalary.value / costOfLiving) * 100;
        final double AYB = ((double) yearlyBonus.value / costOfLiving) * 100;
        final double GYM = (double) gymMembershipAllowance.value;
        final double LT = (double) leaveTime;
        final double RWT = (double) weeklyTeleworkDays;

        score = ((double) comparisonSettings.yearlySalaryWeight / 7) * AYS
                + ((double) comparisonSettings.yearlyBonusWeight / 7) * AYB
                + ((double) comparisonSettings.gymMembershipAllowanceWeight / 7) * GYM
                + ((double) comparisonSettings.leaveTimeWeight / 7) * (LT * AYS / 260)
                - (((double) comparisonSettings.weeklyTeleworkDaysWeight / 7) * (260 - 52 * RWT)
                * (AYS / 260) / 8);
        return score;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " - " + company;
    }
}