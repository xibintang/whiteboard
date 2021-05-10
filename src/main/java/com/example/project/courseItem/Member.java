package com.example.project.courseItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.project.R;

public class Member extends AppCompatActivity {

    private ListView memberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        setTitle("Member");
        memberList = findViewById(R.id.member_list);
        memberList.setAdapter(new MemberListAdapter(Member.this,getIntent().getStringArrayListExtra("memberList")));

    }
}