package com.example.project.courseItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;

public class ExamListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    ExamListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    ExamListAdapter(){}
    @Override
    // how long is the list
    public int getCount() {
        return 0;
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
        private TextView examTitle,examTimeLimit,examDueDate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_exam_list_item,null);
            holder = new ViewHolder();
            holder.examTitle = convertView.findViewById(R.id.exam_list_title);
            holder.examTimeLimit = convertView.findViewById(R.id.exam_time_limit);
            holder.examDueDate = convertView.findViewById(R.id.exam_due_date);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        // retrieve data and input here
        holder.examTitle.setText("Exam Sample");
        holder.examTimeLimit.setText("Sample Time limit");
        holder.examDueDate.setText("Due date: ");

        return convertView;
    }
}
