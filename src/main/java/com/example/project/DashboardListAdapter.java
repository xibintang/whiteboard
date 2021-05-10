package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> notificationList;
    private int notificationCount;
//    private ArrayList<String> arrayList;

    DashboardListAdapter(Context context, ArrayList<String> list, int notificationCount){
        notificationList = list;
        this.notificationCount = notificationCount;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        // retrieve the number of courses from data base
        // I set 5 as a constant for testing
        return notificationCount;
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
        return position;
    }

    static class ViewHolder{
        private TextView dashboardTitle,dashboardName,dashboardContent,dashboardDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Retrieve data here
        // You can make objects, methods here
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_dashboard_list_item,null);
            holder = new ViewHolder();
            holder.dashboardTitle = convertView.findViewById(R.id.dash_board_item_title);
            holder.dashboardName = convertView.findViewById(R.id.dash_board_item_name);
            holder.dashboardContent = convertView.findViewById(R.id.dash_board_item_content);
            holder.dashboardDate = convertView.findViewById(R.id.dash_board_item_date);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

//        For example:
//        arrayList = new ArrayList<>(10);
//        arrayList.add("10");
//        give textView values:
        holder.dashboardTitle.setText(notificationList.get(position*2));
        holder.dashboardName.setText("TBD");
        holder.dashboardContent.setText(notificationList.get(position*2+1));
        holder.dashboardDate.setText("null");

        return convertView;
    }
}
