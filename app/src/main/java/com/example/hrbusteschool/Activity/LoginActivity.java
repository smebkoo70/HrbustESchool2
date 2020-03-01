package com.example.hrbusteschool.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.hrbusteschool.Class.WebServiceGet;
import com.example.hrbusteschool.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    private EditText usertextview;
    private EditText pwdtextview;
    static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/android1?characterEncoding=utf-8&serverTimezone=UTC";
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称
    static final String USER = "root";
    static final String PASS = "";
    //提示框
    private ProgressDialog dialog;
    //服务器返回的数据
    private String infoString;

    Button Login_Button;
    TextView Forgot_textView,Register_textView,back_textView;
    private final int LOGINSUCCESS=0;
    private final int LOGINNOTFOUND=1;
    private final int LOGINEXCEPT=2;
    private final int REGISTERSUCCESS=3;
    private final int REGISTERNOTFOUND=4;
    private final int REGISTEREXCEPT=5;
    private String usernameStr,passwordStr;
    /*@SuppressLint("HandlerLeak")
    Handler handler=new Handler()
    {//消息机制，用来在子线程中更新UI
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){//具体消息，具体显示
                case LOGINSUCCESS:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case LOGINNOTFOUND:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case LOGINEXCEPT:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTERSUCCESS:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTERNOTFOUND:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTEREXCEPT:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Register_textView = findViewById(R.id.register);
        Forgot_textView = findViewById(R.id.forgot_ps);
        back_textView = findViewById(R.id.textView_back);
        Login_Button = findViewById(R.id.LoginButton);
        usertextview = (EditText) findViewById(R.id.usertextview);
        pwdtextview = (EditText) findViewById(R.id.pwdtextview);
        Register_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        Forgot_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LoginActivity","textview forgot已经点击");
                Toast toast = Toast.makeText(LoginActivity.this,"敬请期待",Toast.LENGTH_SHORT);
                toast.show();
                Intent testwebvintent = new Intent(LoginActivity.this,TestWebViewActivity.class);
                startActivity(testwebvintent);
            }
        });
        back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameStr = usertextview.getText().toString().trim();
                passwordStr = pwdtextview.getText().toString().trim();
                if(usernameStr.equals("") || passwordStr.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        dialog = new ProgressDialog(LoginActivity.this);
                        dialog.setTitle("正在登陆");
                        dialog.setMessage("请稍后");
                        dialog.setCancelable(false);//设置可以通过back键取消
                        dialog.show();

                        //设置子线程，分别进行Get和Post传输数据
                        new Thread(new MyThread()).start();


                        //TestLogin();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        //Toast toast = new Toast.makeText();
                        //e.printStackTrace();
                    }
                }

            }
        });
    }
    public class MyThread implements Runnable{
        @Override
        public void run() {
            infoString = WebServiceGet.executeHttpGet(usertextview.getText().toString(),pwdtextview.getText().toString(),"LogLet");//获取服务器返回的数据

            //更新UI，使用runOnUiThread()方法
            showResponse(infoString);
        }
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                if(response.equals("false")){
                    Toast.makeText(LoginActivity.this,"登陆失败,用户名或密码错误", Toast.LENGTH_SHORT).show();
                }else {
                    //info.setText(response);
                    Toast.makeText(LoginActivity.this,"登陆成功！", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
    }




}
