package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:27017/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<AssignmentResult>> call = retrofitInterface.getAssignments();
        call.enqueue(new Callback<List<AssignmentResult>>() {
            @Override
            public void onResponse(Call<List<AssignmentResult>> call, Response<List<AssignmentResult>> response) {
                String msg = " ";
                if(!response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<AssignmentResult> assignments = response.body();

                for(AssignmentResult hw: assignments){
                    msg += "Name: "+hw.getName()+ "\n";
                    msg += "Grade: "+ hw.getGrade() + "\n";
                    msg += "Name: "+hw.getWeight()+ "\n";
                    msg += "Name: "+hw.isReceived()+ "\n";
//                    Log.d("Data",msg);
                }
            }
            @Override
            public void onFailure(Call<List<AssignmentResult>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

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