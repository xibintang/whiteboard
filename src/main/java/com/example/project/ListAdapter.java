package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    ListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        // retrieve the number of courses from data base
        // I set 5 as a constant for testing
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        // return a unique id which corresponds to the item in the list
        // you can set your own
        // can be use to distinguish different courses
        return 0;
    }

    static class ViewHolder{
        private TextView courseTitle,courseProfessor;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_course_list_item,null);
            holder = new ViewHolder();
            holder.courseTitle = convertView.findViewById(R.id.course_list_title);
            holder.courseProfessor = convertView.findViewById(R.id.course_list_professor);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        holder.courseTitle.setText("Sample");
        holder.courseProfessor.setText("Sample professor");

        return convertView;
    }
}
