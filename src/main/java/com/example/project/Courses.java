package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Courses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Courses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView courseList;

    public Courses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Courses.
     */
    // TODO: Rename and change types and number of parameters
    public static Courses newInstance(ArrayList<String> courseListName, ArrayList<Integer> courseListNumber, ArrayList<String> courseListID, int courseCount, String studentID) {
        Courses fragment = new Courses();
        Bundle args = new Bundle();
        // over here, you can transfer data through putString method, or others if you need.
        args.putStringArrayList("courseListName", courseListName);
        args.putIntegerArrayList("courseListNumber", courseListNumber);
        args.putStringArrayList("courseListID", courseListID);
        args.putInt("courseCount",courseCount);
        args.putString("studentID",studentID);

        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(View view, Bundle savedInstanceState) {
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
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        courseList = view.findViewById(R.id.course_list);

        ArrayList<String> courseListName = getArguments().getStringArrayList("courseListName");
        ArrayList<Integer> courseListNumber = getArguments().getIntegerArrayList("courseListNumber");
        ArrayList<String> courseListID = getArguments().getStringArrayList("courseListID");

        courseList.setAdapter(new ListAdapter(view.getContext()
                ,courseListName
                ,courseListNumber
                ,courseListID
                ,getArguments().getInt("courseCount")));
        courseList.setDivider(null);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // parameters: position - allows you to jump into different activities based on the position of the item.
                // intent - jumps to the course menu activity

                for (int i = 0; i < courseListNumber.size(); i++){
                    if ((int)id == courseListNumber.get(i)){
                        Intent intent = new Intent(view.getContext(),CourseMenu.class);
                        intent.putExtra("courseName",courseListName.get(i));
                        intent.putExtra("courseID",courseListID.get(i));
                        intent.putExtra("studentID",getArguments().getString("studentID"));
                        startActivity(intent);
                    }
                }
                // You can use position number to control what happened after clicking
                // the corresponding position item.
                // for example, you can use if (position == etc){ action }
                // it is possible that you transfer data from one activity to another


                // over here, you can get Argument to display the data you put in the bundle.

                // transfer data to course menu through intent.putExtra

                // what data to pass? studentID, courseID

            }
        });
        return view;
    }
}