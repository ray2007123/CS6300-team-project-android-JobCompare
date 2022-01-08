package edu.gatech.seclass.jobcompare6300.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class JobListBaseAdapter extends BaseAdapter {

//    private static final String TAG = JobListBaseAdapter.class.getName();

    private static List<Job> jobs;

    private final LayoutInflater layoutInflater;

    public JobListBaseAdapter(Context context, List<Job> jobs) {
        this.jobs = jobs;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return jobs.size();
    }

    public Object getItem(int position) {
        if (position < 0) {
//            Log.e(TAG, "Negative position");
            return null;
        }
        return jobs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < 0) {
//            Log.e(TAG, "Negative position");
            return null;
        }
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.compare_jobs_row, null);
            holder = new ViewHolder();
            holder.rank = convertView.findViewById(R.id.rank);
            holder.title = convertView.findViewById(R.id.title);
            holder.company = convertView.findViewById(R.id.company);
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rank.setText("" + (position + 1));
        holder.title.setText(jobs.get(position).title);
        if (jobs.get(position).isCurrent) {
            holder.company.setText(jobs.get(position).company + " (CURRENT)");
        } else {
            holder.company.setText(jobs.get(position).company);
        }
        holder.checkBox.setChecked(jobs.get(position).checked);

        return convertView;
    }

    static class ViewHolder {
        TextView rank;
        TextView title;
        TextView company;
        CheckBox checkBox;
    }
}
