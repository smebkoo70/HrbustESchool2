package com.example.hrbusteschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrbusteschool.Class.nav_bar;
import com.example.hrbusteschool.R;

public class TestNavBarActivity extends AppCompatActivity {
    private ImageView hBack;
    private ImageView hHead;
    private ImageView userLine;
    private TextView userName;
    private TextView userVal;
    private nav_bar lishi;
    private nav_bar shoucang;
    private nav_bar version;
    private nav_bar about;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nav_bar);
        hBack = (ImageView) findViewById(R.id.h_back);
        hHead = (ImageView) findViewById(R.id.h_head);
        userLine = (ImageView) findViewById(R.id.user_line);
        userName = (TextView) findViewById(R.id.user_name);
        userVal = (TextView) findViewById(R.id.user_val);
        lishi = (nav_bar) findViewById(R.id.lishi);
        shoucang = (nav_bar) findViewById(R.id.shoucang);
        version = (nav_bar) findViewById(R.id.version);
        about = (nav_bar) findViewById(R.id.about);
    }
}
