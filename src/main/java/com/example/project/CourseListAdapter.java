package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.LinearViewHolder>{

    private Context mContext;
    private OnItemClickListener mListener;

    public CourseListAdapter(Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public CourseListAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_course_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.LinearViewHolder holder, int position) {
        holder.textView.setText("Software Engineering");
        holder.textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Hey", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return 5;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            //textView = itemView.findViewById(R.id.course_list_item);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }
}
