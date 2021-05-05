package com.example.project.courseItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;

public class AssignmentListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    AssignmentListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        private TextView assignmentTitle,assignmentTimeLimit,assignmentDueDate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_assignment_list_item,null);
            holder = new ViewHolder();
            holder.assignmentTitle = convertView.findViewById(R.id.assignment_list_title);
            holder.assignmentTimeLimit = convertView.findViewById(R.id.assignment_time_limit);
            holder.assignmentDueDate = convertView.findViewById(R.id.assignment_due_date);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        // retrieve data from database and input here
        holder.assignmentTitle.setText("Assignment Sample");
        holder.assignmentTimeLimit.setText("Sample Time limit");
        holder.assignmentDueDate.setText("Due date: ");

        return convertView;
    }
}
