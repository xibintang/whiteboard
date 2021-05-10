package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Grades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grades extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Integer> courseGrades;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView overallGradeList;
    private TextView cumulativeGPA;
    public Grades() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Grades.
     */
    // TODO: Rename and change types and number of parameters
    public static Grades newInstance(ArrayList<String> courseNames, ArrayList<Integer> courseGrades, int numOfCourse) {
        Grades fragment = new Grades();
        Bundle args = new Bundle();
        args.putIntegerArrayList("courseGrade", courseGrades);
        args.putStringArrayList("courseNames", courseNames);
        args.putInt("numOfCourse",0);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        overallGradeList = (ListView) view.findViewById(R.id.overall_grade_list);
        overallGradeList.setAdapter(new OverallGradeListAdapter(view.getContext(),getArguments().getStringArrayList("courseNames"),getArguments().getIntegerArrayList("courseGrades"),getArguments().getInt("numOfCourse")));
        overallGradeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        courseGrades = getArguments().getIntegerArrayList("courseGrade");
        int grade = 0;
        for (int i = 0; i < courseGrades.size(); i++){
            grade = grade + courseGrades.get(i);
        }
        if (grade != 0){
            grade = grade / courseGrades.size();
        }

        overallGradeList.setDivider(null);
        cumulativeGPA = view.findViewById(R.id.cumulative_gpa);
        cumulativeGPA.setText(grade + "");

        return view;
    }
}