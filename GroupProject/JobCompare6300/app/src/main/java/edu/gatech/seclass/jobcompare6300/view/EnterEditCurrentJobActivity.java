package edu.gatech.seclass.jobcompare6300.view;

import static edu.gatech.seclass.jobcompare6300.util.IntUtil.isGreaterThanIntMaxValue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;
import edu.gatech.seclass.jobcompare6300.model.CurrentJob;

public class EnterEditCurrentJobActivity extends AppCompatActivity {

    //For logging
    //private static final String TAG = EnterEditCurrentJobActivity.class.getName();
//    private String message;

    final DBController dbController = DBController.getInstance(this);

    private CurrentJob currentJob;
    private EditText currentJobTitle;
    private EditText currentJobCompany;
    private EditText currentJobLocation;
    private EditText currentJobCostOfLiving;
    private EditText currentJobAnnualSalary;
    private EditText currentJobAnnualBonus;
    private EditText currentJobWeeklyTeleworkDays;
    private EditText currentJobLeaveTime;
    private EditText currentJobGymMembershipAllowance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_edit_current_job);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final TextView currentJobDetailsTitle = findViewById(R.id.currentJobDetailsTitle);
        currentJobTitle = findViewById(R.id.currentJobTitle);
        currentJobCompany = findViewById(R.id.currentJobCompany);
        currentJobLocation = findViewById(R.id.currentJobLocation);
        currentJobCostOfLiving = findViewById(R.id.currentJobCostOfLiving);
        currentJobAnnualSalary = findViewById(R.id.currentJobAnnualSalary);
        currentJobAnnualBonus = findViewById(R.id.currentJobAnnualBonus);
        currentJobWeeklyTeleworkDays = findViewById(R.id.currentJobWeeklyTeleworkDays);
        currentJobLeaveTime = findViewById(R.id.currentJobLeaveTime);
        currentJobGymMembershipAllowance = findViewById(R.id.currentJobGymMembershipAllowance);

        boolean currentJobFound = false;
        currentJob = null;
        final List<Map<String, String>> allJobs = dbController.getAllJobs();
        for (Map<String, String> map : allJobs) {
            if (map.containsKey("isCurrent")) {
                final String isCurrent = map.get("isCurrent");
                if ("true".equals(isCurrent)) {
                    currentJobFound = true;
                    currentJob = new CurrentJob().fromMap(map);
                    setFieldsFromCurrentJob();
                    break;
                }
            }
        }
        if (currentJobFound) {
            currentJobDetailsTitle.setText("Edit Current Job Details");
        } else {
            currentJobDetailsTitle.setText("Enter Current Job Details");
        }
    }

    private void setFieldsFromCurrentJob() {
        currentJobTitle.setText(currentJob.title);
        currentJobCompany.setText(currentJob.company);
        currentJobLocation.setText(currentJob.location);
        currentJobCostOfLiving.setText(""+currentJob.costOfLiving);
        currentJobAnnualSalary.setText(""+currentJob.yearlySalary.value);
        currentJobAnnualBonus.setText(""+currentJob.yearlyBonus.value);
        currentJobWeeklyTeleworkDays.setText(""+currentJob.weeklyTeleworkDays);
        currentJobLeaveTime.setText(""+currentJob.leaveTime);
        currentJobGymMembershipAllowance.setText(""+currentJob.gymMembershipAllowance.value);
    }

    public void saveCurrentJob(View view) {
        final CurrentJob tempCurrentJob = new CurrentJob();

        boolean errorEncountered = false;

        tempCurrentJob.title = currentJobTitle.getText().toString();
        if(tempCurrentJob.title.length() == 0){
            currentJobTitle.setError("Invalid title");
            errorEncountered = true;
        }

        tempCurrentJob.company = currentJobCompany.getText().toString();
        if(tempCurrentJob.company.length() == 0){
            currentJobCompany.setError("Invalid company");
            errorEncountered = true;
        }

        tempCurrentJob.location = currentJobLocation.getText().toString();
        if(tempCurrentJob.location.length() == 0){
            currentJobLocation.setError("Invalid location");
            errorEncountered = true;
        }

        if(currentJobCostOfLiving.getText().toString().length() == 0){
            currentJobCostOfLiving.setError("Invalid cost of living");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobCostOfLiving.getText().toString())) {
            currentJobCostOfLiving.setError("Cost of living too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.costOfLiving = Integer.parseUnsignedInt(currentJobCostOfLiving.getText().toString());
            }catch (NumberFormatException nfe){
                currentJobCostOfLiving.setError("Invalid cost of living");
                errorEncountered = true;
            }
        }

        if(currentJobAnnualSalary.getText().toString().length() == 0){
            currentJobAnnualSalary.setError("Invalid annual salary");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobAnnualSalary.getText().toString())) {
            currentJobAnnualSalary.setError("Annual salary too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.yearlySalary.value = Integer.parseUnsignedInt(currentJobAnnualSalary.getText().toString());
            }catch (NumberFormatException nfe){
                currentJobAnnualSalary.setError("Invalid annual salary");
                errorEncountered = true;
            }
        }

        if(currentJobAnnualBonus.getText().toString().length() == 0){
            currentJobAnnualBonus.setError("Invalid annual bonus");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobAnnualBonus.getText().toString())) {
            currentJobAnnualBonus.setError("Annual bonus too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.yearlyBonus.value = Integer.parseUnsignedInt(currentJobAnnualBonus.getText().toString());
            }catch (NumberFormatException nfe){
                currentJobAnnualBonus.setError("Invalid annual bonus");
                errorEncountered = true;
            }
        }

        if(currentJobWeeklyTeleworkDays.getText().toString().length() == 0){
            currentJobWeeklyTeleworkDays.setError("Invalid weekly telework days");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobWeeklyTeleworkDays.getText().toString())) {
            currentJobWeeklyTeleworkDays.setError("Weekly telework days too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.weeklyTeleworkDays = Integer.parseUnsignedInt(currentJobWeeklyTeleworkDays.getText().toString());
                if(tempCurrentJob.weeklyTeleworkDays > 5){
                    currentJobWeeklyTeleworkDays.setError("Weekly telework days is greater than the maximum 5");
                    errorEncountered = true;
                }
            }catch (NumberFormatException nfe){
                currentJobWeeklyTeleworkDays.setError("Invalid weekly telework days");
                errorEncountered = true;
            }
        }

        if(currentJobLeaveTime.getText().toString().length() == 0){
            currentJobLeaveTime.setError("Invalid leave time");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobLeaveTime.getText().toString())) {
            currentJobLeaveTime.setError("Leave time too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.leaveTime = Integer.parseUnsignedInt(currentJobLeaveTime.getText().toString());
            }catch (NumberFormatException nfe){
                currentJobLeaveTime.setError("Invalid leave time");
                errorEncountered = true;
            }
        }

        if(currentJobGymMembershipAllowance.getText().length() == 0){
            currentJobGymMembershipAllowance.setError("Invalid gym membership allowance");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(currentJobGymMembershipAllowance.getText().toString())) {
            currentJobGymMembershipAllowance.setError("Gym membership allowance too high");
            errorEncountered = true;
        }else{
            try {
                tempCurrentJob.gymMembershipAllowance.value = Integer.parseUnsignedInt(currentJobGymMembershipAllowance.getText().toString());
                if(tempCurrentJob.gymMembershipAllowance.value > 500){
                    currentJobGymMembershipAllowance.setError("Gym membership allowance is greater than the maximum 500");
                    errorEncountered = true;
                }
            }catch (NumberFormatException nfe){
                currentJobGymMembershipAllowance.setError("Invalid gym membership allowance");
                errorEncountered = true;
            }
        }

        if(errorEncountered){
            return;
        }

        //Get the id
        if (currentJob == null) {
            //Log.i(TAG, "Id of current job = next id available");
            tempCurrentJob.id = dbController.getNextAvailableJobId();
        } else {
//            Log.i(TAG, "Id of current job = " + currentJob.id);
            tempCurrentJob.id = currentJob.id;
        }

        final Map<String, String> map = tempCurrentJob.toMap();

        if (currentJob == null) {
//            Log.i(TAG, "Inserting current job...");
            dbController.insertJob(map);
        } else {
//            Log.i(TAG, "Updating current job...");
//            final int result =
            dbController.updateJob(map, tempCurrentJob.id);
//            Log.i(TAG, "Updated " + result + " current job.");
        }
        currentJob = tempCurrentJob;

//        message = "Current job saved; id = " + currentJob.id;
//        Log.i(TAG, message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        finish();
    }

    public void cancelCurrentJob(View view) {
        finish();
    }

}