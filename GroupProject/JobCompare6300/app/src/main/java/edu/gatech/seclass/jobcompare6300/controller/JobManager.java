package edu.gatech.seclass.jobcompare6300.controller;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.model.*;
import edu.gatech.seclass.jobcompare6300.view.AdjustComparisonSettingsActivity;
import edu.gatech.seclass.jobcompare6300.view.CompareJobsActivity;
import edu.gatech.seclass.jobcompare6300.view.EnterEditCurrentJobActivity;
import edu.gatech.seclass.jobcompare6300.view.EnterJobOfferActivity;

public class JobManager extends AppCompatActivity {
    private final DBController dbController = DBController.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Button currentJobButton = findViewById(R.id.currentJobButton);
        boolean currentJobFound = false;
        final List<Map<String, String>> allJobs = dbController.getAllJobs();
        for (Map<String, String> map : allJobs) {
            if(map.containsKey("isCurrent")){
                final String isCurrent = map.get("isCurrent");
                if("1".equals(isCurrent) || "true".equals(isCurrent) || "yes".equals(isCurrent)){
                    currentJobFound = true;
                    break;
                }
            }
        }
        if(currentJobFound){
            currentJobButton.setText("EDIT CURRENT JOB DETAILS");
            currentJobButton.setOnClickListener(this::editCurrentJobDetails);
        }else{
            currentJobButton.setText("ENTER CURRENT JOB DETAILS");
            currentJobButton.setOnClickListener(this::enterCurrentJobDetails);
        }

        //Since there is only ever 1 and only 1 ComparisonSetting, let's just insert it here
        if (dbController.getAllComparisonSettings().size() == 0) {
            final ComparisonSettings comparisonSettings = new ComparisonSettings();
            Map<String, String> queryValues = comparisonSettings.toMap();
            dbController.insertComparisonSetting(queryValues);
        }
    }

    @Override
    protected void onDestroy() {
        dbController.close();
        super.onDestroy();
    }

    public void enterCurrentJobDetails(View view) {
        final Intent intent = new Intent(this, EnterEditCurrentJobActivity.class);
        startActivity(intent);
    }

    public void editCurrentJobDetails(View view) {
        final Intent intent = new Intent(this, EnterEditCurrentJobActivity.class);
        startActivity(intent);
    }

    public void enterJobOfferDetails(View view) {
        final Intent intent = new Intent(this, EnterJobOfferActivity.class);
        startActivity(intent);
    }

    public void adjustComparisonSettings(View view){
        final Intent intent = new Intent(this, AdjustComparisonSettingsActivity.class);
        startActivity(intent);
    }

    public void compareJobs(View view){
        final List<Map<String, String>> allJobs = dbController.getAllJobs();
        if(allJobs.size() <= 1){
            Toast.makeText(this, "Not enough jobs to compare", Toast.LENGTH_LONG).show();
            return;
        }
        final Intent intent = new Intent(this, CompareJobsActivity.class);
        startActivity(intent);
    }

}