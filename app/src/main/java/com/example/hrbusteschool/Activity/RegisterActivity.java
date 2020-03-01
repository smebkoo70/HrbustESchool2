package com.example.hrbusteschool.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hrbusteschool.Class.CodeUtils;
import com.example.hrbusteschool.Class.RegisterGet;
import com.example.hrbusteschool.Class.RegisterPost;
import com.example.hrbusteschool.Class.WebServiceGet;
import com.example.hrbusteschool.R;


public class RegisterActivity extends AppCompatActivity {


    //控件定义
    private TextView textViewUsername;
    private EditText editTextUsername;
    private TextView textViewPassword;
    private TextView textViewConfirmpassword;
    private TextView textViewTel;
    private TextView textViewModify;
    private TextView textViewSex;
    private EditText editTextPassword;
    private EditText editTextTel;
    private EditText editTextCode;
    private EditText editTextConfirmpassword;
    private Button buttonReset;
    private Button buttonSumbit;
    private RadioGroup radiogroupSex;
    private RadioButton sexradioMan;
    private RadioButton sexradioWomen;
    private RadioButton radioButton;
    private ImageView codeimage;

    //生成验证码定义
    private Bitmap bitmap;
    private String code;

    //核心注册变量
    private String usernamestr, pwdstr, confirmstr, telstr, codestr, sexstr;
    private String infoString;
    //提示框
    private ProgressDialog dialog;

    private void ClearRegisterInfo() {
        editTextUsername.setText("");
        editTextConfirmpassword.setText("");
        editTextPassword.setText("");
        editTextTel.setText("");
        editTextCode.setText("");
        sexradioMan.setChecked(false);
        sexradioWomen.setChecked(false);
    }

    private void RefreshCode() {
        bitmap = CodeUtils.getInstance().createBitmap();
        code = CodeUtils.getInstance().getCode();
        codeimage.setImageBitmap(bitmap);
        Toast.makeText(RegisterActivity.this, code, Toast.LENGTH_SHORT).show();
    }

    private void selectRadioBtn() {
        radioButton = (RadioButton) findViewById(radiogroupSex.getCheckedRadioButtonId());
        //Toast.makeText(RegisterActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        /*if(radioButton.getText() == "sexradio_man")
        {
            sexstr = "Man";
        }
        else
        {
            sexstr = "Woman";
        }*/
        sexstr = radioButton.getText().toString();
        Toast.makeText(RegisterActivity.this, sexstr, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textViewUsername = (TextView) findViewById(R.id.textView_username);
        editTextUsername = (EditText) findViewById(R.id.editText_username);
        textViewPassword = (TextView) findViewById(R.id.textView_password);
        textViewConfirmpassword = (TextView) findViewById(R.id.textView_confirmpassword);
        textViewTel = (TextView) findViewById(R.id.textView_tel);
        textViewModify = (TextView) findViewById(R.id.textView_modify);
        textViewSex = (TextView) findViewById(R.id.textView_sex);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        editTextTel = (EditText) findViewById(R.id.editText_tel);
        editTextCode = (EditText) findViewById(R.id.editText_code);
        editTextConfirmpassword = (EditText) findViewById(R.id.editText_confirmpassword);
        buttonReset = (Button) findViewById(R.id.button_reset);
        buttonSumbit = (Button) findViewById(R.id.button_sumbit);
        radiogroupSex = (RadioGroup) findViewById(R.id.radiogroup_sex);
        sexradioMan = (RadioButton) findViewById(R.id.sexradio_man);
        sexradioWomen = (RadioButton) findViewById(R.id.sexradio_women);
        codeimage = (ImageView) findViewById(R.id.codeimage);

        //获取工具类生成的图片验证码对象
        bitmap = CodeUtils.getInstance().createBitmap();
        //获取当前图片验证码的对应内容用于校验
        code = CodeUtils.getInstance().getCode();

        codeimage.setImageBitmap(bitmap);
        //点击图片 刷新验证码
        codeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshCode();

            }
        });


        //清空屏幕
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearRegisterInfo();
                /*editTextUsername.setText("");
                editTextConfirmpassword.setText("");
                editTextPassword.setText("");
                editTextTel.setText("");
                editTextCode.setText("");
                sexradioMan.setChecked(false);
                sexradioWomen.setChecked(false);*/
            }
        });
        radiogroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectRadioBtn();


            }
        });


        //注册事件
        buttonSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取注册信息
                usernamestr = editTextUsername.getText().toString().trim();
                pwdstr = editTextPassword.getText().toString().trim();
                confirmstr = editTextConfirmpassword.getText().toString().trim();
                telstr = editTextTel.getText().toString().trim();
                codestr = editTextCode.getText().toString().trim();
                /*if (sexradioMan.isSelected() == true) {
                    sexstr = "man";
                }
                if (sexradioWomen.isSelected() == true) {
                    sexstr = "woman";
                }*/
                if (usernamestr.equals("") || pwdstr.equals("") || confirmstr.equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    RefreshCode();
                } else if (!pwdstr.equals(confirmstr)) {
                    Toast.makeText(RegisterActivity.this, "请确认密码一致！", Toast.LENGTH_SHORT).show();
                    ClearRegisterInfo();
                    RefreshCode();

                }
                //无视大小写比较验证码
                else if (!codestr.equalsIgnoreCase(CodeUtils.getInstance().getCode())) {
                    Toast.makeText(RegisterActivity.this, "验证码不正确！", Toast.LENGTH_SHORT).show();
                    RefreshCode();
                    //ClearRegisterInfo();
                }
                /*else if(radiogroupSex.isSelected()==false)
                {

                    Toast.makeText(RegisterActivity.this, "请选择性别！", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(RegisterActivity.this, sexstr, Toast.LENGTH_SHORT).show();
                    RefreshCode();
                }*/
                else {
                    /*if (sexradioMan.isSelected() == true) {
                        sexstr = "man";
                    }
                    if (sexradioWomen.isSelected() == true) {
                        sexstr = "woman";
                    }*/
                    try {
                        dialog = new ProgressDialog(RegisterActivity.this);
                        dialog.setTitle("正在注册");
                        dialog.setMessage("请稍后");
                        dialog.setCancelable(false);//设置可以通过back键取消
                        dialog.show();
                        //Toast.makeText(RegisterActivity.this, sexstr, Toast.LENGTH_SHORT).show();

                        //设置子线程，分别进行Get和Post传输数据
                        new Thread(new MyThread()).start();



                    } catch (Exception e) {
                        System.out.println(e.toString());
                        //Toast toast = new Toast.makeText();
                        //e.printStackTrace();
                    }
                }

            }
        });
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            infoString = RegisterGet.executeHttpGet(editTextUsername.getText().toString(), editTextPassword.getText().toString(), editTextTel.getText().toString(), sexstr, "RegLet");//获取服务器返回的数据

            //更新UI，使用runOnUiThread()方法
            showResponse(infoString);
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                if (response.equals("false")) {
                    Toast.makeText(RegisterActivity.this, "注册失败!", Toast.LENGTH_SHORT).show();
                } else {
                    //info.setText(response);
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
    }
}
