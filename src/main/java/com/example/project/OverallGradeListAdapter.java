package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OverallGradeListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    OverallGradeListAdapter(Context context){
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
        private TextView courseTitle,courseProfessor,courseGrade;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_overall_grade_list_item,null);
            holder = new ViewHolder();
            holder.courseTitle = convertView.findViewById(R.id.overall_grade_list_title);
            holder.courseProfessor = convertView.findViewById(R.id.overall_grade_list_professor);
            holder.courseGrade = convertView.findViewById(R.id.overall_grade);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        holder.courseGrade.setText("4.0");

        return convertView;
    }
}
