package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DiscussionListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private ArrayList<String> arrayList;

    DiscussionListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    // how long is the list
    public int getCount() {
        // retrieve the number of courses from data base
        // I set 5 as a constant for testing
        return 1;
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
        private TextView discussionName,discussionContent,discussionComment;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Retrieve data here
        // You can make objects, methods here
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_discussion_list_item,null);
            holder = new ViewHolder();
            holder.discussionName = convertView.findViewById(R.id.discussion_list_item_name);
            holder.discussionContent = convertView.findViewById(R.id.discussion_list_item_content);
            holder.discussionComment = convertView.findViewById(R.id.discussion_list_item_comment);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

//        For example:
//        arrayList = new ArrayList<>(10);
//        arrayList.add("10");
//        give textView values:
        holder.discussionName.setText("Xibin");
        holder.discussionContent.setText("This is the discussion area where you can post anything!");
        holder.discussionComment.setText("12");


        return convertView;
    }
}
