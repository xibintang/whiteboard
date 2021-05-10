package com.example.project;

import com.google.gson.annotations.SerializedName;

public class AssignmentResult {
    private String _id;
    @SerializedName("course")
    private String courseID;
    @SerializedName("student")
    private String studentID;
    private String name;
    private int grade;
    private int weight;
    private boolean received;
    private String submission;

    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getID() {
        return _id;
    }
    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getWeight() {
        return weight;
    }

    public String getSubmission() {
        return submission;
    }


    public boolean isReceived() {
        return received;
    }
}