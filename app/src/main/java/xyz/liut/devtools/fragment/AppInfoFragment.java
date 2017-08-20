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
import butterknife.ButterKnife;
import xyz.liut.devtools.R;
import xyz.liut.devtools.ToolBarFragment;
import xyz.liut.devtools.adapter.AppPagerAdapter;

/**
 * AppInfo
 * Created by liut2 on 2017/8/6.
 */
public class AppInfoFragment extends ToolBarFragment {

    @BindView(R.id.tab_layout_app)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_app)
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        toolbar.setTitle("appInfo");
        viewPager.setAdapter(new AppPagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
