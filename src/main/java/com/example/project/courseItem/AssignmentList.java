package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.R;

public class AssignmentList extends AppCompatActivity {

    private ListView assignmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_list);
        assignmentList = findViewById(R.id.assignment_list);
        assignmentList.setAdapter(new AssignmentListAdapter(AssignmentList.this));
        assignmentList.setDivider(null);
        assignmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AssignmentList.this,Assignment.class);
                startActivity(intent);
            }
        });
    }
}