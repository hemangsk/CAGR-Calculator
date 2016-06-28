package com.hemangkumar.cagrcalculator;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar();

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        adapter.addFragment(new CalcFrag(), "CAGR");
        adapter.addFragment(new AbsFrag(), "ABSOLUTE RETURN");
        adapter.addFragment(new ConvFrag(), "CONVERTER");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }



}
class FragAdapter extends FragmentPagerAdapter{

    public ArrayList<Fragment> FragList = new ArrayList<>();
    public ArrayList<String> FragmentTitleList = new ArrayList<>();

    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragList.get(position);
    }

    @Override
    public int getCount() {
        return FragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        FragList.add(fragment);
        FragmentTitleList.add(title);
    }
}