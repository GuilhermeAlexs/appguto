package com.fatura.view.fatura;

import com.fatura.view.fatura.calls.CallsView;
import com.fatura.view.fatura.internet.InternetView;
import com.fatura.view.fatura.total.TotalView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            return new CallsView();
        case 1:
            return new InternetView();
        case 2:
            return new TotalView();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}