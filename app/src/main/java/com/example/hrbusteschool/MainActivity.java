package com.example.hrbusteschool;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hrbusteschool.Adapter.FragmentAdapter;
import com.example.hrbusteschool.Fragment.LuntanFragment;
import com.example.hrbusteschool.Fragment.NewPersonFragment;
import com.example.hrbusteschool.Fragment.PersonalFragment;
import com.example.hrbusteschool.Fragment.newsfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonalFragment.OnFragmentInteractionListener, newsfragment.OnFragmentInteractionListener, LuntanFragment.OnFragmentInteractionListener,NewPersonFragment.OnFragmentInteractionListener {
    private TextView mTextMessage;
    // PersonalFragment personalFragmenthome;
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    //private PersonalFragment personalFragment;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void Request() {
        //获取相机拍摄读写权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //版本判断
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_newsreport:
                    //mTextMessage.setText("资讯");
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_eclass:
                    mTextMessage.setText(R.string.title_myclass);
                    return true;
                case R.id.navigation_bbs:
                    //mTextMessage.setText("论坛");
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText("个人");
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Request();

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);

        viewPager = findViewById(R.id.main_viewpager);
        //fragments.add(new PersonalFragment());
        fragments.add(new newsfragment());
        fragments.add(new LuntanFragment());
        fragments.add(new NewPersonFragment());
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }




}
