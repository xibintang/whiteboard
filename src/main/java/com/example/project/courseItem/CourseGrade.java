package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;

public class CourseGrade extends AppCompatActivity {

    private TextView courseGrade;
    private ListView listView;
    private TextView courseGradeScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_grade);
        setTitle("Grade");
        courseGrade = findViewById(R.id.course_grade);
        listView = findViewById(R.id.course_grade_list);
        listView.setAdapter(new CourseGradeListAdapter(this));

    }
}