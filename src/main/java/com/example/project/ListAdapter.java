package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> courseListName;
    private ArrayList<Integer> courseListNumber;
    private ArrayList<String> courseListID;
    private int courseCount;
//    private ArrayList<String> arrayList;

    ListAdapter(Context context, ArrayList<String> courseListName, ArrayList<Integer> courseListNumber, ArrayList<String> courseListID, int courseCount){
        this.courseListName = courseListName;
        this.courseListNumber = courseListNumber;
        this.courseListID = courseListID;
        this.courseCount = courseCount;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        // retrieve the number of courses from data base
        // I set 5 as a constant for testing
        return courseCount;
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
        return courseListNumber.get(position);
    }

    static class ViewHolder{
        private TextView courseTitle,courseProfessor;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Retrieve data here
        // You can make objects, methods here
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

//        For example:
//        arrayList = new ArrayList<>(10);
//        arrayList.add("10");
//        give textView values:
        holder.courseTitle.setText(courseListName.get(position));
        holder.courseProfessor.setText("TBD");


        return convertView;
    }
}
