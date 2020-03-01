package com.example.hrbusteschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hrbusteschool.R;
//AppCompatActivity
public class SendPostActivity extends Activity {
    private Button btSend;
    private EditText edTieziTitle;
    private EditText edTieziContent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_post);
        btSend = (Button) findViewById(R.id.bt_send);
        edTieziTitle = (EditText) findViewById(R.id.ed_tiezi_title);
        edTieziContent = (EditText) findViewById(R.id.ed_tiezi_content);
    }
}
