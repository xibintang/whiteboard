package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.courseItem.AssignmentList;
import com.example.project.courseItem.CourseGrade;
import com.example.project.courseItem.ExamList;
import com.example.project.courseItem.Member;

import java.lang.reflect.Array;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseMenu extends AppCompatActivity {

    private ImageView resources,grades,exams,assignment,announcement,member;
    private ListView listView;
    private TextView courseTitle;
    private int numOfAssignment = 0;
    private ArrayList<String> assignmentName;
    private ArrayList<Integer> assignmentGrade;
    private ArrayList<Integer> assignmentWeight;
    private ArrayList<String> assignmentID;
    private ArrayList<String> memberList;
    private String courseID;
    private String studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);

        // retrieve the key info from the last activity:
        courseID = getIntent().getStringExtra("courseID");
        studentID = getIntent().getStringExtra("studentID");

        // assign arraylist:
        assignmentGrade = new ArrayList<>();
        assignmentName = new ArrayList<>();
        assignmentWeight = new ArrayList<>();
        assignmentID = new ArrayList<>();
        memberList = new ArrayList<>();
        // retrieve data from database:
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<AssignmentResult>> call = retrofitInterface.getAssignments();
        Call<List<CourseResult>> call2 = retrofitInterface.getCourses();
        Call<ArrayList<StudentResult>> call3 = retrofitInterface.getStudents();

        // retrieve student info to get the member list:
        call3.enqueue(new Callback<ArrayList<StudentResult>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentResult>> call, Response<ArrayList<StudentResult>> response) {
                ArrayList<StudentResult> studentResults = response.body();
                for (StudentResult student: studentResults){
                    String[] array = student.getCourses();
                    for (int i = 0; i < array.length; i++){
                        if (array[i].equals(courseID)){
                            memberList.add(student.getName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentResult>> call, Throwable t) {

            }
        });

        // get the assignment list
        call.enqueue(new Callback<List<AssignmentResult>>() {
            @Override
            public void onResponse(Call<List<AssignmentResult>> call, Response<List<AssignmentResult>> response) {
                if(!response.isSuccessful()){
                    Log.d("RESPONSE(fail): ", String.valueOf(response.code()));
                }

                // do action:
                List<AssignmentResult> assignments = response.body();
                for(AssignmentResult hw: assignments){
                    if (hw.getCourseID().equals(courseID) && hw.getStudentID().equals(studentID)){
                        // if this is true, then this assignment belongs to this course and this student:
                        assignmentName.add(hw.getName());
                        assignmentGrade.add(hw.getGrade());
                        assignmentWeight.add(hw.getWeight());
                        assignmentID.add(hw.getID());
                        numOfAssignment += 1;
                    }
                    // if not, pass
                }

            }
            @Override
            public void onFailure(Call<List<AssignmentResult>> call, Throwable t) {
                Log.d("ERROR", String.valueOf(t));
            }
        });

        courseTitle = findViewById(R.id.course_title);
        courseTitle.setText(getIntent().getStringExtra("courseName"));
        resources = findViewById(R.id.resources);
        grades = findViewById(R.id.grades);
        exams = findViewById(R.id.exams);
        assignment = findViewById(R.id.assignment);
        announcement = findViewById(R.id.announcement);
        member = findViewById(R.id.member);
        listView = findViewById(R.id.discussion_list);
        listView.setAdapter(new DiscussionListAdapter(this));
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

        // what data to pass here? assignment, memberList, grades, etc.
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.resources:
                    break;
                case R.id.grades:
                    // send to grades:
                    intent = new Intent(CourseMenu.this, CourseGrade.class);
                    intent.putStringArrayListExtra("assignmentName",assignmentName);
                    intent.putIntegerArrayListExtra("assignmentGrade",assignmentGrade);
                    intent.putIntegerArrayListExtra("assignmentWeight",assignmentWeight);
                    startActivity(intent);
                    break;
                case R.id.exams:
                    intent = new Intent(CourseMenu.this, ExamList.class);
                    startActivity(intent);
                    break;
                case R.id.assignment:
                    // send to assignment:
                    intent = new Intent(CourseMenu.this, AssignmentList.class);
                    intent.putExtra("numOfAssignment",numOfAssignment);
                    intent.putStringArrayListExtra("assignmentName",assignmentName);
                    intent.putStringArrayListExtra("assignmentID",assignmentID);
                    intent.putIntegerArrayListExtra("assignmentWeight",assignmentWeight);
                    startActivity(intent);
                    break;
                case R.id.announcement:
                    break;
                case R.id.member:
                    intent = new Intent(CourseMenu.this, Member.class);
                    intent.putStringArrayListExtra("memberList",memberList);
                    startActivity(intent);
                    break;
            }
        }
    }
}