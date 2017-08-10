package xyz.liut.devtools;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 带有ToolBar的Fragment
 * Created by liut on 17-8-6.
 */
public abstract class ToolBarFragment extends Fragment {

    private static final String TOOL_TAG = "ToolBarFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainActivity activity;
    private DrawerLayout drawer;    //来自Activity

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        drawer = activity.getDrawer();
        toolbar.setTitle(getToolBarTitle());
        setToolBar(toolbar);

    }

    /**
     * 为activity设置toolbar
     * 为toolbar设置监听
     *
     * @param toolbar
     */
    public void setToolBar(Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = getActionBarDrawerToggle(toolbar);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * @param toolbar
     * @return ToolBar监听 and drawer 绑定
     */
    private ActionBarDrawerToggle getActionBarDrawerToggle(Toolbar toolbar) {
        return new ActionBarDrawerToggle
                (activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d(TOOL_TAG, "k");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TOOL_TAG, "c");
            }
        };
    }

    /**
     * @return 返回标题
     */
    protected abstract String getToolBarTitle();

}
