package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.R;

public class ExamList extends AppCompatActivity {

    private ListView examListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        examListView = findViewById(R.id.exam_list);
        setTitle("Exam");
        examListView.setAdapter(new ExamListAdapter(ExamList.this));
        examListView.setDivider(null);
        examListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExamList.this,Exam.class);
                startActivity(intent);
            }
        });
    }
}