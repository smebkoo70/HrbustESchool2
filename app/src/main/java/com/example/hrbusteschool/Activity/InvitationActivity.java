package com.example.hrbusteschool.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.hrbusteschool.R;


//这个活动是点击论坛的每个帖子所转到，用于显示帖子的内容。
public class InvitationActivity extends AppCompatActivity {

    private TextView Inv_Title;
    private String titlemsg;
    @NonNull
    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }
    public void sendValue(String value) {
        //Log.e(TAG,value);
        titlemsg = value;

    }

    private void InitInvitation()
    {
        //sendValue();
        //String titlemsg ="";
        Inv_Title.setText(titlemsg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
    }
}
