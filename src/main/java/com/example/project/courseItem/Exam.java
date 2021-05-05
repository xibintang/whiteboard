package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class Exam extends AppCompatActivity {

    private Button grade,checkForPlag,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        setTitle("Exam");
        grade = findViewById(R.id.btn_exam_grade);
        checkForPlag = findViewById(R.id.btn_exam_check_for_plag);
        edit = findViewById(R.id.btn_exam_edit);

        TextComparator textComparator = new TextComparator("abcdsaf","fajfasd");
        //System.out.println(textComparator.compareTexts());
        checkForPlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Exam.this);
                // output the result in setMessage method:
                builder.setTitle("Result").setMessage("Match rate: " + textComparator.compareTexts()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

    }
}