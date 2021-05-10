package com.example.project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @GET("/assignment/")
    Call<List<AssignmentResult>> getAssignments();

    @GET("/course/")
    Call<List<CourseResult>> getCourses();

    @GET("/student/")
    Call<ArrayList<StudentResult>> getStudents();

    @GET("/notification/")
    Call<List<NotificationResult>> getNotifications();
}
