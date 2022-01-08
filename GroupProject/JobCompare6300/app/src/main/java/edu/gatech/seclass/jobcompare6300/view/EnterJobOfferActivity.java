package edu.gatech.seclass.jobcompare6300.view;

import static edu.gatech.seclass.jobcompare6300.util.IntUtil.isGreaterThanIntMaxValue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;
import edu.gatech.seclass.jobcompare6300.model.JobOffer;

public class EnterJobOfferActivity extends AppCompatActivity {

    //For logging
//    private static final String TAG = EnterJobOfferActivity.class.getName();

    final DBController dbController = DBController.getInstance(this);

    private EditText jobOfferTitle;
    private EditText jobOfferCompany;
    private EditText jobOfferLocation;
    private EditText jobOfferCostOfLiving;
    private EditText jobOfferAnnualSalary;
    private EditText jobOfferAnnualBonus;
    private EditText jobOfferWeeklyTeleworkDays;
    private EditText jobOfferLeaveTime;
    private EditText jobOfferGymMembershipAllowance;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_job_offer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        jobOfferTitle = findViewById(R.id.jobOfferTitle);
        jobOfferCompany = findViewById(R.id.jobOfferCompany);
        jobOfferLocation = findViewById(R.id.jobOfferLocation);
        jobOfferCostOfLiving = findViewById(R.id.jobOfferCostOfLiving);
        jobOfferAnnualSalary = findViewById(R.id.jobOfferAnnualSalary);
        jobOfferAnnualBonus = findViewById(R.id.jobOfferAnnualBonus);
        jobOfferWeeklyTeleworkDays = findViewById(R.id.jobOfferWeeklyTeleworkDays);
        jobOfferLeaveTime = findViewById(R.id.jobOfferLeaveTime);
        jobOfferGymMembershipAllowance = findViewById(R.id.jobOfferGymMembershipAllowance);
    }

    @Override
    protected void onDestroy() {
        dbController.close();
        super.onDestroy();
    }

    public void saveJobOffer(View view){
        final JobOffer tempJobOffer = new JobOffer();

        boolean errorEncountered = false;

        tempJobOffer.title = jobOfferTitle.getText().toString();
        if(tempJobOffer.title.length() == 0){
            jobOfferTitle.setError("Invalid title");
            errorEncountered = true;
        }

        tempJobOffer.company = jobOfferCompany.getText().toString();
        if(tempJobOffer.company.length() == 0){
            jobOfferCompany.setError("Invalid company");
            errorEncountered = true;
        }

        tempJobOffer.location = jobOfferLocation.getText().toString();
        if(tempJobOffer.location.length() == 0){
            jobOfferLocation.setError("Invalid location");
            errorEncountered = true;
        }

        if(jobOfferCostOfLiving.getText().toString().length() == 0){
            jobOfferCostOfLiving.setError("Invalid cost of living");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferCostOfLiving.getText().toString())) {
            jobOfferCostOfLiving.setError("Cost of living too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.costOfLiving = Integer.parseUnsignedInt(jobOfferCostOfLiving.getText().toString());
            }catch (NumberFormatException nfe){
                jobOfferCostOfLiving.setError("Invalid cost of living");
                errorEncountered = true;
            }
        }

        if(jobOfferAnnualSalary.getText().toString().length() == 0){
            jobOfferAnnualSalary.setError("Invalid annual salary");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferAnnualSalary.getText().toString())) {
            jobOfferAnnualSalary.setError("Annual salary too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.yearlySalary.value = Integer.parseUnsignedInt(jobOfferAnnualSalary.getText().toString());
            }catch (NumberFormatException nfe){
                jobOfferAnnualSalary.setError("Invalid annual salary");
                errorEncountered = true;
            }
        }

        if(jobOfferAnnualBonus.getText().toString().length() == 0){
            jobOfferAnnualBonus.setError("Invalid annual bonus");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferAnnualBonus.getText().toString())) {
            jobOfferAnnualBonus.setError("Annual bonus too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.yearlyBonus.value = Integer.parseUnsignedInt(jobOfferAnnualBonus.getText().toString());
            }catch (NumberFormatException nfe){
                jobOfferAnnualBonus.setError("Invalid annual bonus");
                errorEncountered = true;
            }
        }

        if(jobOfferWeeklyTeleworkDays.getText().toString().length() == 0){
            jobOfferWeeklyTeleworkDays.setError("Invalid weekly telework days");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferWeeklyTeleworkDays.getText().toString())) {
            jobOfferWeeklyTeleworkDays.setError("Weekly telework days too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.weeklyTeleworkDays = Integer.parseUnsignedInt(jobOfferWeeklyTeleworkDays.getText().toString());
                if(tempJobOffer.weeklyTeleworkDays > 5){
                    jobOfferWeeklyTeleworkDays.setError("Weekly telework days is greater than the maximum 5");
                    errorEncountered = true;
                }
            }catch (NumberFormatException nfe){
                jobOfferWeeklyTeleworkDays.setError("Invalid weekly telework days");
                errorEncountered = true;
            }
        }

        if(jobOfferLeaveTime.getText().toString().length() == 0){
            jobOfferLeaveTime.setError("Invalid leave time");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferLeaveTime.getText().toString())) {
            jobOfferLeaveTime.setError("Leave time too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.leaveTime = Integer.parseUnsignedInt(jobOfferLeaveTime.getText().toString());
            }catch (NumberFormatException nfe){
                jobOfferLeaveTime.setError("Invalid leave time");
                errorEncountered = true;
            }
        }

        if(jobOfferGymMembershipAllowance.getText().length() == 0){
            jobOfferGymMembershipAllowance.setError("Invalid gym membership allowance");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(jobOfferGymMembershipAllowance.getText().toString())) {
            jobOfferGymMembershipAllowance.setError("Gym membership allowance too high");
            errorEncountered = true;
        }else{
            try {
                tempJobOffer.gymMembershipAllowance.value = Integer.parseUnsignedInt(jobOfferGymMembershipAllowance.getText().toString());
                if(tempJobOffer.gymMembershipAllowance.value > 500){
                    jobOfferGymMembershipAllowance.setError("Gym membership allowance is greater than the maximum 500");
                    errorEncountered = true;
                }
            }catch (NumberFormatException nfe){
                jobOfferGymMembershipAllowance.setError("Invalid gym membership allowance");
                errorEncountered = true;
            }
        }

        if(errorEncountered){
            return;
        }

        //Limit the number of job offers to 100
        List<Map<String, String>> allJobs = dbController.getAllJobs();
        if(allJobs.size() >= 100) {
            int countOfOffers = 0;
            for (int i = 0; i < allJobs.size(); i++) {
                final Map<String, String> job = allJobs.get(i);
                if (job.containsKey("isCurrent") && !"true".equals(job.get("isCurrent"))) {
                    countOfOffers++;
                }
                if (countOfOffers >= 100) {
                    Toast.makeText(this, "Already at limit of job offers (100)", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

        tempJobOffer.id = dbController.getNextAvailableJobId();
        final Map<String, String> map = tempJobOffer.toMap();
//        Log.i(TAG, "Inserting job offer...");
        dbController.insertJob(map);

//        String message = "Job offer saved; id = " + tempJobOffer.id;
//        Log.i(TAG, message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        allJobs = dbController.getAllJobs();
        if(allJobs.size() > 1){
            //from https://www.tutorialspoint.com/android/android_alert_dialoges.htm
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Would you like to compare this to the current job or another job offer, enter another job offer, or go back to main menu?");
            alertDialogBuilder.setPositiveButton("Compare",
                            (arg0, arg1) -> {
                                final Intent intent = new Intent(this, CompareJobsActivity.class);
                                startActivity(intent);
                                finish();
                            });
            alertDialogBuilder.setNegativeButton("Enter Another Job Offer",
                    (arg0, arg1) -> {
                        final Intent intent = new Intent(this, EnterJobOfferActivity.class);
                        startActivity(intent);
                        finish();
                    });
            alertDialogBuilder.setNeutralButton("Main Menu",
                    (dialog, which) -> {
                        finish();
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else {
            finish();
        }
    }

    public void cancelJobOffer(View view){
        finish();
    }
}