package com.example.hrbusteschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hrbusteschool.R;

public class EditPersonInfoActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private ImageView headIcon;
    private Button buttonChangeimg;
    private TextView textView21;
    private TextView username;
    private TextView textView3;
    private EditText byname;
    private View view;
    private View view2;
    private TextView textView2;
    private Spinner sex;
    private View view3;
    private TextView textView5;
    private EditText email;
    private View view4;
    private TextView textView11;
    private EditText collage;
    private View view7;
    private View view5;
    private TextView textView12;
    private EditText major;
    private View view6;
    private TextView textView7;
    private EditText tele;
    private TextView textView6;
    private EditText dnumber;
    private EditText says;
    private Button pSub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_info);
        backBtn = (ImageButton) findViewById(R.id.back_btn);
        headIcon = (ImageView) findViewById(R.id.head_icon);
        buttonChangeimg = (Button) findViewById(R.id.button_changeimg);
        textView21 = (TextView) findViewById(R.id.textView21);
        username = (TextView) findViewById(R.id.username);
        textView3 = (TextView) findViewById(R.id.textView3);
        byname = (EditText) findViewById(R.id.byname);
        view = (View) findViewById(R.id.view);
        view2 = (View) findViewById(R.id.view2);
        textView2 = (TextView) findViewById(R.id.textView2);
        sex = (Spinner) findViewById(R.id.sex);
        view3 = (View) findViewById(R.id.view3);
        textView5 = (TextView) findViewById(R.id.textView5);
        email = (EditText) findViewById(R.id.email);
        view4 = (View) findViewById(R.id.view4);
        textView11 = (TextView) findViewById(R.id.textView11);
        collage = (EditText) findViewById(R.id.collage);
        view7 = (View) findViewById(R.id.view7);
        view5 = (View) findViewById(R.id.view5);
        textView12 = (TextView) findViewById(R.id.textView12);
        major = (EditText) findViewById(R.id.major);
        view6 = (View) findViewById(R.id.view6);
        textView7 = (TextView) findViewById(R.id.textView7);
        tele = (EditText) findViewById(R.id.tele);
        textView6 = (TextView) findViewById(R.id.textView6);
        dnumber = (EditText) findViewById(R.id.dnumber);
        says = (EditText) findViewById(R.id.says);
        pSub = (Button) findViewById(R.id.p_sub);
    }
}
