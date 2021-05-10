package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private Button login_button;
    private EditText email;
    private EditText password;

    private ArrayList<String> notificationContent;
    private int notificationCount = 0;

    private ArrayList<String> courseListName;
    private ArrayList<Integer> courseListNumber;
    private ArrayList<String> courseListID;

    private ArrayList<String> studentCourseList;

    private ArrayList<Integer> assignmentGrades;
    private ArrayList<String> assignmentID;
    private ArrayList<ArrayList<Integer>> studentGradeList;

    private ArrayList<String> studentEmail;
    private ArrayList<String> studentPassword;
    private ArrayList<String> studentID;
    private ArrayList<String[]> studentCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.login_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        // assign to different arrayList:
        notificationContent = new ArrayList<>();
        courseListName = new ArrayList<>();
        courseListNumber = new ArrayList<>();
        courseListID = new ArrayList<>();

        studentEmail = new ArrayList<>();
        studentPassword = new ArrayList<>();
        studentID = new ArrayList<>();
        studentCourses = new ArrayList<>();

        studentCourseList = new ArrayList<>();
        assignmentID = new ArrayList<>();
        assignmentGrades = new ArrayList<>();
        studentGradeList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<NotificationResult>> call2 = retrofitInterface.getNotifications();
        Call<List<CourseResult>> call3 = retrofitInterface.getCourses();
        Call<List<AssignmentResult>> call4 = retrofitInterface.getAssignments();

        // retrieve notification info
        call2.enqueue(new Callback<List<NotificationResult>>() {
            @Override
            public void onResponse(Call<List<NotificationResult>> call, Response<List<NotificationResult>> response) {
                if(!response.isSuccessful()){
                    Log.d("RESPONSE(fail): ", String.valueOf(response.code()));
                }
                // add the content to the content arrayList:
                List<NotificationResult> notificationResults = response.body();
                for (NotificationResult list: notificationResults){
                    notificationContent.add(list.getCourseNumber() + "");
                    notificationContent.add(list.getContent());
                    notificationCount = notificationCount + 1;
                }
            }

            @Override
            public void onFailure(Call<List<NotificationResult>> call, Throwable t) {
                Log.d("ERROR", String.valueOf(t));
            }
        });

        // retrieve course info
        call3.enqueue(new Callback<List<CourseResult>>() {
            @Override
            public void onResponse(Call<List<CourseResult>> call, Response<List<CourseResult>> response) {
                if(!response.isSuccessful()){
                    Log.d("RESPONSE(fail): ", String.valueOf(response.code()));
                }

                List<CourseResult> courses = response.body();
                for(CourseResult c: courses){

                    // store the courseName and courseNumber into the array
                    courseListID.add(c.get_id());
                    courseListName.add(c.getCourseName());
                    courseListNumber.add(c.getCourseNumber());
                }
            }

            @Override
            public void onFailure(Call<List<CourseResult>> call, Throwable t) {
                Log.d("ERROR", String.valueOf(t));
            }
        });

        // retrieve assignment info:
        call4.enqueue(new Callback<List<AssignmentResult>>() {
            @Override
            public void onResponse(Call<List<AssignmentResult>> call, Response<List<AssignmentResult>> response) {
                // get the assignment:
                List<AssignmentResult> assignmentResults = response.body();
                int grade = 0;
                int count = 0;
                for (AssignmentResult assignmentResult: assignmentResults){
                    //
                    for (int i = 0; i < courseListID.size(); i++){
                        for (int j = 0; j < assignmentResults.size(); j++){
                            if (courseListID.get(i).equals(assignmentResult.getCourseID())){
                                grade = grade + assignmentResult.getGrade();
                                count = count + 1;
                            }
                        }
                        if (count != 0) {
                            grade = grade/count;
                            assignmentGrades.add(grade);
                        }
                        assignmentGrades.add(100);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AssignmentResult>> call, Throwable t) {

            }
        });

        Call<ArrayList<StudentResult>> call = retrofitInterface.getStudents();

        // retrieve student info
        call.enqueue(new Callback<ArrayList<StudentResult>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentResult>> call, Response<ArrayList<StudentResult>> response) {
                ArrayList<StudentResult> studentList = response.body();

                for (StudentResult list:studentList) {
                    // retrieve student email and password, then store them in array:
                    studentEmail.add(list.getEmail());
                    studentPassword.add(list.getPassword());
                    studentID.add(list.getID());
                    studentCourses.add(list.getCourses());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentResult>> call, Throwable t) {
                Log.d("ERROR", String.valueOf(t));
            }
        });

        // set onclick listener
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < studentEmail.size(); i++){
                    if (studentEmail.get(i).equals(email.getText().toString()) && studentPassword.get(i).equals(password.getText().toString())){

                        Intent intent = new Intent(Login.this,MainActivity.class);
                        // transfer student info
                        intent.putExtra("id",studentID.get(i));

                        // put all the courses into the array:
                        for (int j = 0; j < studentCourses.get(i).length; j++){
                            studentCourseList.add(studentCourses.get(i)[j]);
                        }
                        intent.putStringArrayListExtra("studentCourseList",studentCourseList);

                        // transfer notification info
                        intent.putStringArrayListExtra("notificationList",notificationContent);
                        intent.putExtra("notificationCount",notificationCount);

                        // transfer course info
                        intent.putStringArrayListExtra("courseName",courseListName);
                        intent.putIntegerArrayListExtra("courseNumber",courseListNumber);

                        // transfer grades
                        intent.putIntegerArrayListExtra("courseGrades",assignmentGrades);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "User Not exist or Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                }
                // if the student is not in the array:

            }

        });
    }
}