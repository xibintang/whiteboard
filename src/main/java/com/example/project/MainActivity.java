package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView navDashboard,navCourses,navGrades,navNotification,navProfile;
    private Dashboard dashboard;
    private Grades grades;
    private Courses courses;
    private Notification notification;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navDashboard = findViewById(R.id.nav_dashboard);
        navCourses = findViewById(R.id.nav_courses);
        navGrades = findViewById(R.id.nav_grades);
        navNotification = findViewById(R.id.nav_notification);
        navProfile = findViewById(R.id.nav_profile);

        dashboard = new Dashboard();
        grades = new Grades();
        courses = new Courses();
        notification = new Notification();
        profile = new Profile();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,dashboard).commitAllowingStateLoss();
        setListener();

    }

    private void setListener(){
        OnClick onclick = new OnClick();
        navDashboard.setOnClickListener(onclick);
        navCourses.setOnClickListener(onclick);
        navGrades.setOnClickListener(onclick);
        navNotification.setOnClickListener(onclick);
        navProfile.setOnClickListener(onclick);
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.nav_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,dashboard).commitAllowingStateLoss();
                    break;
                case R.id.nav_courses:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,courses).commitAllowingStateLoss();
                    break;
                case R.id.nav_grades:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,grades).commitAllowingStateLoss();
                    break;
                case R.id.nav_notification:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,notification).commitAllowingStateLoss();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profile).commitAllowingStateLoss();
                    break;
            }

        }
    }
}