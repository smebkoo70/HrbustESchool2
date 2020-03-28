package com.example.hrbusteschool.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.hrbusteschool.R;

public class TestReFreshActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeRefreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_re_fresh);


    }

}
