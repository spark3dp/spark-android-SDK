package com.autodesk.spark.sdk.example.fragments;

/**
 * Created by ronnyremsnik on 5/14/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autodesk.spark.sdk.example.R;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import org.w3c.dom.Text;

import java.util.Locale;


public class DriveFragment extends Fragment {

    public final int PAGE_SUM  = 3;
    public final int PAGE_GENERAL = 0;
    public final int PAGE_CREATE_ASSET = 1;
    public final int PAGE_CREATE_FILE = 2;


    public View mainView;
    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPagerAdapter;
    public CirclePageIndicator circleIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_drive, container, false);

        initUI();

        return mainView;
    }

    public void initUI()
    {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)mainView.findViewById(R.id.pager_drive);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // END_INCLUDE (setup_view_pager)

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectNavigationPage(position);
            }
        });

        //Bind the title indicator to the adapter

        circleIndicator = (CirclePageIndicator)mainView.findViewById(R.id.indicator);
        circleIndicator.setViewPager(mViewPager);

        circleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectNavigationPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        selectNavigationPage(PAGE_GENERAL);

    }

    public void selectNavigationPage(int index)
    {
        TextView textTitle = (TextView)mainView.findViewById(R.id.text_drive_label);

        if (index == PAGE_GENERAL)
        {
            textTitle.setText(getString(R.string.drive_title_section1));
        }
        else if (index == PAGE_CREATE_ASSET)
        {
            textTitle.setText(getString(R.string.drive_title_section2));
        }
        else if (index == PAGE_CREATE_FILE)
        {
            textTitle.setText(getString(R.string.drive_title_section3));
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        // END_INCLUDE (fragment_pager_adapter)



        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position == PAGE_GENERAL)
            {
                fragment = new DriveGeneralFragment();
            }
            else if (position == PAGE_CREATE_ASSET)
            {
                fragment = new DriveCreateAssetFragment();
            }
            else if (position == PAGE_CREATE_FILE)
            {
                fragment = new DriveCreateFileFragment();
            }

            return fragment;
        }
        @Override
        public int getCount() {
            return PAGE_SUM;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case PAGE_GENERAL:
                    return getString(R.string.title_section1).toUpperCase(l);
                case PAGE_CREATE_ASSET:
                    return getString(R.string.title_section2).toUpperCase(l);
                case PAGE_CREATE_FILE:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

}