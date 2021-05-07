package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.R;

public class Assignment extends AppCompatActivity {

    private Button checkForPlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        setTitle("Assignment");

        TextComparator textComparator = new TextComparator("","");
        checkForPlag = findViewById(R.id.btn_assignment_check_for_plag);
        checkForPlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do the algorithm:
                AlertDialog.Builder builder = new AlertDialog.Builder(Assignment.this);
                // output the result in setMessage method:
                builder.setTitle("Result")
                        .setMessage("Match rate: " + textComparator.compareTexts())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }
}