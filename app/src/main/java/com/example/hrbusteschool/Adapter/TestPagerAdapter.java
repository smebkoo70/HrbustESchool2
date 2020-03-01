package com.example.hrbusteschool.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TestPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public TestPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }
}

/*    //在activity中初始化ViewPager 以及设置Adapter
    ViewPager viewPager=findViewById(R.id.viewPager);
//设置预加载页面
viewPager.setOffscreenPageLimit(2);
//fragmentList为Fragment集合
        TestPagerAdapter adapter=new TestPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

}*/
