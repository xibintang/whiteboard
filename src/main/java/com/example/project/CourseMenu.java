package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project.courseItem.AssignmentList;
import com.example.project.courseItem.ExamList;
import com.example.project.courseItem.Member;

import java.nio.channels.InterruptedByTimeoutException;

public class CourseMenu extends AppCompatActivity {

    private ImageView resources,grades,exams,assignment,announcement,member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);

        resources = findViewById(R.id.resources);
        grades = findViewById(R.id.grades);
        exams = findViewById(R.id.exams);
        assignment = findViewById(R.id.assignment);
        announcement = findViewById(R.id.announcement);
        member = findViewById(R.id.member);
        setOnClick();
    }

    private void setOnClick(){
        OnClick onClick = new OnClick();
        resources.setOnClickListener(onClick);
        grades.setOnClickListener(onClick);
        exams.setOnClickListener(onClick);
        announcement.setOnClickListener(onClick);
        member.setOnClickListener(onClick);
        assignment.setOnClickListener(onClick);

    }
    class OnClick implements View.OnClickListener{
        Intent intent = null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.resources:
                    break;
                case R.id.grades:
                    break;
                case R.id.exams:
                    intent = new Intent(CourseMenu.this, ExamList.class);
                    startActivity(intent);
                    break;
                case R.id.assignment:
                    intent = new Intent(CourseMenu.this, AssignmentList.class);
                    startActivity(intent);
                    break;
                case R.id.announcement:
                    break;
                case R.id.member:
                    intent = new Intent(CourseMenu.this, Member.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}