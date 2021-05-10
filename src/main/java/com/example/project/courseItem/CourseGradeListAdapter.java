package com.example.project.courseItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

public class CourseGradeListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> assignmentName;
    private ArrayList<Integer> assignmentGrade;
    private ArrayList<Integer> assignmentWeight;

    CourseGradeListAdapter(Context context, ArrayList<String> assignmentName, ArrayList<Integer> assignmentGrade, ArrayList<Integer> assignmentWeight){
        this.context = context;
        this.assignmentGrade = assignmentGrade;
        this.assignmentName = assignmentName;
        this.assignmentWeight = assignmentWeight;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        return assignmentName.size();
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
        private TextView courseGradeTitle,courseGradeScore;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_course_grade_list_item,null);
            holder = new ViewHolder();
            holder.courseGradeTitle = convertView.findViewById(R.id.course_grade_list_title);
            holder.courseGradeScore = convertView.findViewById(R.id.course_grade_list_score);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        // retrieve data from database and input here

        holder.courseGradeTitle.setText(assignmentName.get(position));
        holder.courseGradeScore.setText(assignmentGrade.get(position).toString());

        return convertView;
    }
}
