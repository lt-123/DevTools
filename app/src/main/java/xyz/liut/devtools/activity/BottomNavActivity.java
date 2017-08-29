package xyz.liut.devtools.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import xyz.liut.devtools.R;
import xyz.liut.devtools.fragment.AppInfoFragment;
import xyz.liut.devtools.fragment.DevInfoFragment;
import xyz.liut.devtools.fragment.LittleToolsFragment;

/**
 * 底部导航
 * <p>
 * Created by Liut2 on 2017/8/29.
 */
public class BottomNavActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private Fragment appInfoFragment;
    private Fragment devInfoFragment;
    private Fragment littleToolsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
//        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        appInfoFragment = new AppInfoFragment();
        devInfoFragment = new DevInfoFragment();
        littleToolsFragment = new LittleToolsFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content, littleToolsFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, appInfoFragment).commit();
                return true;
            case R.id.navigation_dashboard:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, devInfoFragment).commit();
                return true;
            case R.id.navigation_notifications:
                fm.beginTransaction()
                        .replace(R.id.frame_layout, littleToolsFragment).commit();
                return true;
        }
        return false;
    }
}
