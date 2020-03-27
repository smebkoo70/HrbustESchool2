package com.example.hrbusteschool.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hrbusteschool.Class.ActivityCollectorUtil;
import com.example.hrbusteschool.Fragment.PersonalFragment;
import com.example.hrbusteschool.WebClass.WebServiceGet;
import com.example.hrbusteschool.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    Button Login_Button,Register_Button;
    TextView Forgot_textView,Register_textView,back_textView;

    private CheckBox rembox;


    private SharedPreferences pref;

    private SharedPreferences.Editor editor;
    private String userNameValue,passwordValue;
    private final int LOGINSUCCESS=0;
    private final int LOGINNOTFOUND=1;
    private final int LOGINEXCEPT=2;
    private final int REGISTERSUCCESS=3;
    private final int REGISTERNOTFOUND=4;
    private final int REGISTEREXCEPT=5;
    private String usernameStr,passwordStr,remname,rempwd;
    public static Boolean LogStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_login2);
        //Register_textView = findViewById(R.id.register);
        Forgot_textView = findViewById(R.id.forgot_ps);
        //back_textView = findViewById(R.id.textView_back);
        Login_Button = findViewById(R.id.LoginButton);
        Register_Button = findViewById(R.id.register);
        usertextview = (EditText) findViewById(R.id.usertextview);
        pwdtextview = (EditText) findViewById(R.id.pwdtextview);
        rembox = (CheckBox) findViewById(R.id.rememberbox);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isRemember = pref.getBoolean("rememberbox",false);

        //当点击了记住密码后执行下面的步骤
        if(isRemember){
            remname = pref.getString("usernameStr","");
            rempwd = pref.getString("passwordStr","");

            usertextview.setText(remname);
            pwdtextview.setText(rempwd);
            rembox.setChecked(true);
        }

        Register_Button.setOnClickListener(new View.OnClickListener() {
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
                //Intent testwebvintent = new Intent(LoginActivity.this,TestNavBarActivity.class);
                //startActivity(testwebvintent);
            }
        });
        /*back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
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
                        passwordStr = md5Password(passwordStr);
                        dialog = new ProgressDialog(LoginActivity.this);
                        dialog.setTitle("正在登陆");
                        dialog.setMessage("请稍后");
                        dialog.setCancelable(false);//设置可以通过back键取消
                        dialog.show();

                        //设置子线程，分别进行Get和Post传输数据
                        new Thread(new MyThread()).start();

                        //当点击了记住密码后执行下面的步骤
                        if(isRemember){
                            remname = pref.getString("usernameStr","");
                            rempwd = pref.getString("passwordStr","");

                            usertextview.setText(remname);
                            pwdtextview.setText(rempwd);
                            rembox.setChecked(true);
                        }

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
            infoString = WebServiceGet.executeHttpGet(usertextview.getText().toString(),passwordStr,"LogLet");//获取服务器返回的数据
            userNameValue = usertextview.getText().toString();
            passwordValue = pwdtextview.getText().toString();
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
                    editor = pref.edit();
                    if (rembox.isChecked()) {
                        /*editor.putBoolean("rememberbox", true);
                        editor.putString("usernameStr", remname);
                        editor.putString("passwordStr", rempwd);*/
                        //editor.putBoolean("rememberbox", true);
                        editor.putString("usernameStr", userNameValue);
                        editor.putString("passwordStr", passwordValue);
                        editor.commit();
                    } else {
                        editor.clear();
                    }
                    editor.apply();

                    Toast.makeText(LoginActivity.this,"登陆成功！", Toast.LENGTH_SHORT).show();
                    LogStatus = true;

                    Toast.makeText(LoginActivity.this,remname+rempwd, Toast.LENGTH_SHORT).show();
                    finish();

                    //Activity.finish

                }
                dialog.dismiss();
            }
        });
    }

    //MD5算法
    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
    //普通方式
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }



}
