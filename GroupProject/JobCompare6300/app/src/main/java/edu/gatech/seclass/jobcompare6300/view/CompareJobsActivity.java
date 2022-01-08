package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.model.Job;
import edu.gatech.seclass.jobcompare6300.model.User;

public class CompareJobsActivity extends AppCompatActivity {
//    private static final String TAG = CompareJobsActivity.class.getName();
    String message;

    final DBController dbController = DBController.getInstance(this);

    private TextView compareResult;
    private List<Job> jobs;
    private JobListBaseAdapter jobListBaseAdapter;

    private ComparisonSettings comparisonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);
    }

    @Override
    protected void onResume() {
        super.onResume();

        compareResult = findViewById(R.id.compareResult);

        comparisonSettings = (User.getComparisonSettings() != null) ?
                User.getComparisonSettings() : getComparisonSettings();

        final List<Map<String, String>> allJobs = dbController.getAllJobs();
        jobs = new ArrayList<>();
        for (final Map<String, String> map : allJobs) {
            final Job job = new Job().fromMap(map);
            job.getJobScore(comparisonSettings);
            jobs.add(job);
        }

        jobs.sort(new JobComparator());

        final ListView listView = findViewById(R.id.listView);

        jobListBaseAdapter = new JobListBaseAdapter(this, jobs);
        listView.setAdapter(jobListBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                final Job job = (Job) listView.getItemAtPosition(position);
                job.checked = (!job.checked);

                jobListBaseAdapter.notifyDataSetChanged();
            }
        });

        compareResult.setText("");
    }

    @Override
    protected void onDestroy() {
        dbController.close();
        super.onDestroy();
    }

    private ComparisonSettings getComparisonSettings() {
        final DBController dbController = DBController.getInstance(this);
        final List<Map<String, String>> allComparisonSettings = dbController.getAllComparisonSettings();
        if (allComparisonSettings.size() != 1) {
//            message = "Wrong number of ComparisonSettings: " + allComparisonSettings.size();
//            Log.w(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        final ComparisonSettings comparisonSettings = new ComparisonSettings().fromMap(allComparisonSettings.get(0));
        User.setComparisonSettings(comparisonSettings);
        return comparisonSettings;
    }

    public void compareClick(View view) {
        final List<Job> jobsToCompare = new ArrayList<>();
        for (int i = 0; i < jobs.size(); i++) {
            final Job job = (Job) jobListBaseAdapter.getItem(i);
            if (job.checked) {
                if(jobsToCompare.size() == 2){
                    Toast.makeText(this, "Too many jobs selected", Toast.LENGTH_LONG).show();
                    return;
                }
                jobsToCompare.add(job);
            }
        }
        if(jobsToCompare.size() != 2){
            Toast.makeText(this, "Too few jobs selected", Toast.LENGTH_LONG).show();
            return;
        }
        final Job job1 = jobsToCompare.get(0);
        final Job job2 = jobsToCompare.get(1);

        if (job1 == null || job2 == null) {
            message = "No jobs to compare.";
//            Log.i(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            compareResult.setText(message);
            return;
        }
        if (job1.id == job2.id) {
            compareResult.setText("Jobs are the same");
            return;
        } else {
//            message = "Job 1 id: " + job1.id + "; Job 2 id: " + job2.id;
//            Log.i(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        final double job1Score = job1.getJobScore(comparisonSettings);
        final double job2Score = job2.getJobScore(comparisonSettings);

        if (job1Score > job2Score) {
            compareResult.setText(job1.title + " - " + job1.company + " (score = "
                    + String.format("%,.2f", job1Score) + ") is better than "
                    + job2.title + " - " + job2.company + " (score = " + String.format("%,.2f", job2Score) + ")");
        } else if (job1Score < job2Score) {
            compareResult.setText(job2.title + " - " + job2.company + " (score = "
                    + String.format("%,.2f", job2Score) + ") is better than "
                    + job1.title + " - " + job1.company + " (score = " + String.format("%,.2f", job1Score) + ")");
        } else {
            compareResult.setText("Jobs are equal  (score = " + String.format("%,.2f", job1Score) + ")");
        }
    }

    public void backCompare(View view) {
        finish();
    }

    static final class JobComparator implements Comparator<Job> {
    	public int compare(final Job a, final Job b) {
            final Double aScore = a.score;
            final Double bScore = b.score;
	    	//to sort descending:
	    	return bScore.compareTo(aScore);
	    }
	}
}