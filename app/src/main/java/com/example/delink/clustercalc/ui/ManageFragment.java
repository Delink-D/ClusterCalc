package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.delink.clustercalc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFragment extends Fragment {


    public ManageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage, container, false);

        final TabLayout tabLayout = (TabLayout)view.findViewById(R.id.my_grades_tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Group I"));
        tabLayout.addTab(tabLayout.newTab().setText("Group II"));
        tabLayout.addTab(tabLayout.newTab().setText("Group III"));
        tabLayout.addTab(tabLayout.newTab().setText("Group IV"));
        tabLayout.addTab(tabLayout.newTab().setText("Group V"));

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.my_grades_viewPager);

        viewPager.setAdapter(new PageAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public class PageAdapter extends FragmentStatePagerAdapter{

        int mNumOfTabs;

        public PageAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    GroupIFragment tabItem0 = new GroupIFragment();
                    return  tabItem0;
                case 1:
                    GroupIIFragment tabItem1 = new GroupIIFragment();
                    return tabItem1;
                case 2:
                    GroupIIIFragment tabItem2 = new GroupIIIFragment();
                    return tabItem2;
                case 3:
                    GroupIVFragment tabItem3 = new GroupIVFragment();
                    return tabItem3;
                case 4:
                    GroupVFragment tabItem4 = new GroupVFragment();
                    return tabItem4;
                default:
                    return null;
            }
        }
    }

}
