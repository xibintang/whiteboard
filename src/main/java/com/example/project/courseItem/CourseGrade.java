package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

public class CourseGrade extends AppCompatActivity {

    private TextView courseGrade;
    private ListView listView;
    private TextView courseGradeScore;
    private double score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_grade);
        setTitle("Grade");
        courseGradeScore = findViewById(R.id.course_grade_list_score);
        courseGrade = findViewById(R.id.course_grade);
        listView = findViewById(R.id.course_grade_list);
        ArrayList<String> assignmentName = getIntent().getStringArrayListExtra("assignmentName");
        ArrayList<Integer> assignmentGrade = getIntent().getIntegerArrayListExtra("assignmentGrade");
        ArrayList<Integer> assignmentWeight = getIntent().getIntegerArrayListExtra("assignmentWeight");
        listView.setAdapter(new CourseGradeListAdapter(this,assignmentName,assignmentGrade,assignmentWeight));
        for (int i = 0; i < assignmentGrade.size(); i++){
            score = score + assignmentGrade.get(i);
        }
        score = (score / assignmentGrade.size());

        courseGrade.setText(score + "");
    }
}