package com.example.project.courseItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.AssignmentResult;
import com.example.project.R;
import com.example.project.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssignmentListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int numOfAssignment;
    private ArrayList<String> nameList;
    private ArrayList<String> assignmentID;

    AssignmentListAdapter(Context context,int numOfAssignment,ArrayList<String> nameList,ArrayList<String> assignmentID){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.numOfAssignment = numOfAssignment;
        this.assignmentID = assignmentID;
        this.nameList = nameList;

    }
    @Override
    // how long is the list
    public int getCount() {
        return numOfAssignment;
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
        holder.assignmentTitle.setText(nameList.get(position));
        holder.assignmentTimeLimit.setText("No limit");
        holder.assignmentDueDate.setText("Due date: N/A");

        return convertView;
    }
}
