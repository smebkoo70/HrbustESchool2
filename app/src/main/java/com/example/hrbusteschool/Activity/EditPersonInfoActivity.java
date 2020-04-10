package com.example.hrbusteschool.Activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.hrbusteschool.Class.FileManager;
import com.example.hrbusteschool.Class.PermissionManager;
import com.example.hrbusteschool.R;

import java.io.File;
import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

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

    private static final int REQUEST_CODE_ALBUM = 100;//打开相册
    private static final int REQUEST_CODE_CAMERA = 101;//打开相机
    private static      int REQUEST_CAMERA       = 1;
    private static      int REQUEST_CHOOSE_PHOTO = 2;

    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //打开系统相机按钮
    public void buttonCameraClick(){
        //判断是否有权限
        if (!PermissionManager.checkCameraPermission((PopupMenu.OnMenuItemClickListener) this, this, REQUEST_CAMERA)) {
            try {
                //打开相机
                openCamera();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //打开系统图库
    public void buttonPictrueClick(){
        //判断是否有权限
        if (!PermissionManager.checkPhotoPermission(this, this, REQUEST_CHOOSE_PHOTO)) {
            //打开系统图库
            openGallery();
        }
    }

    //打开系统相机
    private void openCamera() throws IOException {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = FileManager.createFileIfNeed("filepath.png");
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(file);
        } else {
            uri = FileProvider.getUriForFile(this, "link.android.file.provider", file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 1);
    }

    //打开系统图库
    private void openGallery() {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 2);
    }



    private File cameraSavePath;//拍照照片路径
    private Uri uri;//照片uri

    //激活相机操作
    private void goCamera() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(EditPersonInfoActivity.this, "com.example.hrbusteschool.Activity", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        EditPersonInfoActivity.this.startActivityForResult(intent, 1);
    }

    private void showPopupMenu(View view) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(EditPersonInfoActivity.this, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.photo, popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 控件每一个item的点击事件
                switch (item.getItemId())
                {
                    case R.id.paizhao:
                        if (!PermissionManager.checkCameraPermission(this, EditPersonInfoActivity.this, REQUEST_CAMERA)) {
                            try {
                                //打开相机
                                openCamera();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        /*getPermission();
                        goCamera();*/
                        /*Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //系统常量， 启动相机的关键
                        EditPersonInfoActivity.this.startActivityForResult(openCameraIntent,1);*/
                        //startActivityForResult(openCameraIntent,); // 参数常量为自定义的request code, 在取返回结果时有用


                        break;
                    case R.id.select:
                        break;
                }
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
            }
        });
        popupMenu.show();

    }




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
        buttonChangeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
                /*//创建弹出式菜单对象（最低版本11）
                //第二个参数是绑定的那个view
                PopupMenu popup = new PopupMenu(EditPersonInfoActivity.this,view);
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.photo, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) EditPersonInfoActivity.this);
                //显示(这一行代码不要忘记了)
                popup.show();*/

            }
        });
    }
}
