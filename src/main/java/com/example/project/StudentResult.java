package com.example.project;

public class StudentResult {
    private String _id;
    private String email;
    private String password;
    private String[] courses;
    private int totalGrade;
    private String name;

    public String getID() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public String[] getCourses() {
        return courses;
    }

}