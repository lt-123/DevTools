package xyz.liut.devtools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.adapter.AppPagerAdapter;
import xyz.liut.devtools.base.ToolBarFragment;

/**
 * AppInfo
 * Created by liut2 on 2017/8/6.
 */
public class AppInfoFragment extends ToolBarFragment {

    @BindView(R.id.tab_layout_app)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_app)
    ViewPager viewPager;

    @Override
    protected int getToolBarTitle() {
        return R.string.app_info_fragment_title;
    }

    @Override
    protected int getContent() {
        return R.layout.fragment_app_info;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(new AppPagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
