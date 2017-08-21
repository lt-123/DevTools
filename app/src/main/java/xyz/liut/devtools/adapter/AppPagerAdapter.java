package xyz.liut.devtools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.liut.devtools.fragment.AppListFragment;

/**
 * pager adapter
 * <p>
 * Created by liut on 17-8-20.
 */

public class AppPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"user", "system"};

//    private Fragment fragment = new AppListFragment();

    public AppPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new AppListFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
