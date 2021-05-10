package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.AssignmentResult;
import com.example.project.R;
import com.example.project.RetrofitInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Assignment extends AppCompatActivity {

    private Button checkForPlag;
    private String assignmentName;
    private String assignmentID;
    private ArrayList<String> assignmentSubmissionList;
    private String ownSubmission;
    private double plagResult = 0;
    private TextView assignmentTitle;
    private TextView assignmentWeight;
    private int weightPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        setTitle("Assignment");

        assignmentName = getIntent().getStringExtra("assignmentName");
        assignmentID = getIntent().getStringExtra("assignmentID");
        weightPoint = getIntent().getIntExtra("assignmentWeight",0);

        assignmentSubmissionList = new ArrayList<String>();

        assignmentTitle = findViewById(R.id.assignment_title);
        assignmentWeight = findViewById(R.id.assignment_weight);
        assignmentTitle.setText(assignmentName);
        assignmentWeight.setText(weightPoint + "");

        // compare the text:
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<AssignmentResult>> result = retrofitInterface.getAssignments();
        result.enqueue(new Callback<List<AssignmentResult>>() {
            @Override
            public void onResponse(Call<List<AssignmentResult>> call, Response<List<AssignmentResult>> response) {
                List<AssignmentResult> assignmentResults = response.body();
                for (AssignmentResult assignmentResult : assignmentResults){
                    if (assignmentResult.getID().equals(assignmentID)){
                        ownSubmission = assignmentResult.getSubmission();
                    }else{
                        // a list of submission from other student
                        assignmentSubmissionList.add(assignmentResult.getSubmission());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AssignmentResult>> call, Throwable t) {

            }
        });

        // use submissionlist to compare
        for (int i = 0; i < assignmentSubmissionList.size(); i++){
            TextComparator textComparator = new TextComparator(ownSubmission,assignmentSubmissionList.get(i));
            if (plagResult < textComparator.compareTexts()){
                // if the result is greater than before, replace it.
                plagResult = textComparator.compareTexts();
            }
            // if is smaller than before, ignore
        }

        checkForPlag = findViewById(R.id.btn_assignment_check_for_plag);
        checkForPlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do the algorithm:
                AlertDialog.Builder builder = new AlertDialog.Builder(Assignment.this);
                // output the result in setMessage method:
                builder.setTitle("Result")
                        .setMessage("Match rate: " + plagResult + "%")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }
}