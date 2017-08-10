package xyz.liut.devtools;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.liut.devtools.fragment.AppInfoFragment;
import xyz.liut.devtools.fragment.DevInfoFragment;
import xyz.liut.devtools.fragment.LittleToolsFragment;

/**
 * 主界面
 *
 * @author Liut
 */
public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private FragmentManager fm;
    private Fragment appInfoFragment;
    private Fragment devInfoFragment;
    private Fragment littleToolsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this); //设置侧边栏的点击事件

        appInfoFragment = new AppInfoFragment();
        devInfoFragment = new DevInfoFragment();
        littleToolsFragment = new LittleToolsFragment();

        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frame_layout, appInfoFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_app:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, appInfoFragment).commit();
                break;
            case R.id.nav_dev:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, devInfoFragment).commit();
                break;
            case R.id.nav_tools:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, littleToolsFragment).commit();
                break;
            default:
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * @return 返回DrawerLayout对象，供Fragment绑定ToolBar
     */
    public DrawerLayout getDrawer() {
        return drawer;
    }

}
