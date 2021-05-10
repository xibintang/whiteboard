package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.AssignmentResult;
import com.example.project.R;
import com.example.project.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssignmentList extends AppCompatActivity {

    private ListView assignmentList;
    private int numOfAssignment = 0;
    private ArrayList<String> assignmentName;
    private ArrayList<String> assignmentID;
    private ArrayList<Integer> assignmentWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_list);
        numOfAssignment = getIntent().getIntExtra("numOfAssignment",0);
        assignmentName = getIntent().getStringArrayListExtra("assignmentName");
        assignmentID = getIntent().getStringArrayListExtra("assignmentID");
        assignmentWeight = getIntent().getIntegerArrayListExtra("assignmentWeight");

        assignmentList = findViewById(R.id.assignment_list);
        assignmentList.setAdapter(new AssignmentListAdapter(AssignmentList.this,numOfAssignment,assignmentName,assignmentID));
        assignmentList.setDivider(null);
        assignmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AssignmentList.this,Assignment.class);
                intent.putExtra("assignmentName",assignmentName.get(position));
                intent.putExtra("assignmentID",assignmentID.get(position));
                intent.putExtra("assignmentWeight",assignmentWeight.get(position));
                startActivity(intent);
            }
        });
    }
}