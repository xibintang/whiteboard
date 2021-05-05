package com.example.project.courseItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;

public class MemberListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    MemberListAdapter(Context context){
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
        private TextView member;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_member_list_item,null);
            holder = new ViewHolder();
            holder.member = convertView.findViewById(R.id.member);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        // give textView values:
        holder.member.setText("Student");

        return convertView;
    }
}
