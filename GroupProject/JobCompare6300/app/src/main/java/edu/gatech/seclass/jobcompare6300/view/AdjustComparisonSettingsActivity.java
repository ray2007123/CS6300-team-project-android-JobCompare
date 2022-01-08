package edu.gatech.seclass.jobcompare6300.view;

import static edu.gatech.seclass.jobcompare6300.util.IntUtil.isGreaterThanIntMaxValue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.model.User;

public class AdjustComparisonSettingsActivity extends AppCompatActivity {
//    private static final String TAG = AdjustComparisonSettingsActivity.class.getName();
//    String message;

    final DBController dbController = DBController.getInstance(this);

    private EditText annualSalaryWeight;
    private EditText annualBonusWeight;
    private EditText teleworkWeight;
    private EditText leaveTimeWeight;
    private EditText gymAllowanceWeight;
    private ComparisonSettings currentComparisonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_comparison_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        annualSalaryWeight = findViewById(R.id.annualSalaryWeight);
        annualBonusWeight = findViewById(R.id.annualBonusWeight);
        teleworkWeight = findViewById(R.id.teleworkWeight);
        leaveTimeWeight = findViewById(R.id.leaveTimeWeight);
        gymAllowanceWeight = findViewById(R.id.gymAllowanceWeight);

        final List<Map<String, String>> allComparisonSettings = dbController.getAllComparisonSettings();
        if(allComparisonSettings.size() != 1){
//            message = "Wrong number of ComparisonSettings: " + allComparisonSettings.size();
//            Log.e(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        currentComparisonSettings = new ComparisonSettings().fromMap(allComparisonSettings.get(0));

        annualSalaryWeight.setText(""+currentComparisonSettings.yearlySalaryWeight);
        annualBonusWeight.setText(""+currentComparisonSettings.yearlyBonusWeight);
        teleworkWeight.setText(""+currentComparisonSettings.weeklyTeleworkDaysWeight);
        leaveTimeWeight.setText(""+currentComparisonSettings.leaveTimeWeight);
        gymAllowanceWeight.setText(""+currentComparisonSettings.gymMembershipAllowanceWeight);

        User.setComparisonSettings(currentComparisonSettings);
    }

    @Override
    protected void onDestroy() {
        dbController.close();
        super.onDestroy();
    }

    public void saveComparisonSettings(View view) {
        //set settings
        final ComparisonSettings tempComparisonSettings = new ComparisonSettings();

        boolean errorEncountered = false;

        if(annualSalaryWeight.getText().toString().length() == 0){
            annualSalaryWeight.setError("Invalid annual salary weight");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(annualSalaryWeight.getText().toString())) {
            annualSalaryWeight.setError("Annual salary weight too high");
            errorEncountered = true;
        }else{
            try {
                tempComparisonSettings.yearlySalaryWeight = Integer.parseUnsignedInt(annualSalaryWeight.getText().toString());
            }catch (NumberFormatException nfe){
                annualSalaryWeight.setError("Invalid annual salary weight");
                errorEncountered = true;
            }
        }

        if(annualBonusWeight.getText().toString().length() == 0){
            annualBonusWeight.setError("Invalid annual bonus weight");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(annualBonusWeight.getText().toString())) {
            annualBonusWeight.setError("Annual bonus weight too high");
            errorEncountered = true;
        }else{
            try {
                tempComparisonSettings.yearlyBonusWeight = Integer.parseUnsignedInt(annualBonusWeight.getText().toString());
            }catch (NumberFormatException nfe){
                annualBonusWeight.setError("Invalid annual bonus weight");
                errorEncountered = true;
            }
        }

        if(teleworkWeight.getText().toString().length() == 0){
            teleworkWeight.setError("Invalid telework weight");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(teleworkWeight.getText().toString())) {
            teleworkWeight.setError("Telework weight too high");
            errorEncountered = true;
        }else{
            try {
                tempComparisonSettings.weeklyTeleworkDaysWeight = Integer.parseUnsignedInt(teleworkWeight.getText().toString());
            }catch (NumberFormatException nfe){
                teleworkWeight.setError("Invalid telework weight");
                errorEncountered = true;
            }
        }

        if(leaveTimeWeight.getText().toString().length() == 0){
            leaveTimeWeight.setError("Invalid leave time weight");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(leaveTimeWeight.getText().toString())) {
            leaveTimeWeight.setError("Leave time weight too high");
            errorEncountered = true;
        }else{
            try {
                tempComparisonSettings.leaveTimeWeight = Integer.parseUnsignedInt(leaveTimeWeight.getText().toString());
            }catch (NumberFormatException nfe){
                leaveTimeWeight.setError("Invalid leave time weight");
                errorEncountered = true;
            }
        }

        if(gymAllowanceWeight.getText().toString().length() == 0){
            gymAllowanceWeight.setError("Invalid gym membership weight");
            errorEncountered = true;
        }else if (isGreaterThanIntMaxValue(gymAllowanceWeight.getText().toString())) {
            gymAllowanceWeight.setError("Gym membership weight too high");
            errorEncountered = true;
        }else{
            try {
                tempComparisonSettings.gymMembershipAllowanceWeight = Integer.parseUnsignedInt(gymAllowanceWeight.getText().toString());
            }catch (NumberFormatException nfe){
                gymAllowanceWeight.setError("Invalid gym membership weight");
                errorEncountered = true;
            }
        }

        if(errorEncountered){
            return;
        }

        //update database
        final Map<String, String> map = tempComparisonSettings.toMap();
//        Log.i(TAG, "Updating ComparisonSettings...");
        final int result = dbController.updateComparisonSetting(map);
//        Log.i(TAG, "Updated " + result + " ComparisonSettings.");

        currentComparisonSettings = tempComparisonSettings;
        User.setComparisonSettings(currentComparisonSettings);

//        message = "ComparisonSettings saved";
//        Log.i(TAG, message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        finish();
    }

    public void cancelComparisonSettings(View view) {
//        message = "Cancelling adjust comparison settings";
//        Log.i(TAG, message);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        finish();
    }
}