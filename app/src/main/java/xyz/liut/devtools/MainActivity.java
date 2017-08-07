package xyz.liut.devtools;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.liut.devtools.fragment.AppInfoFragment;
import xyz.liut.devtools.fragment.DevInfoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fm;
    private AppInfoFragment appInfoFragment;
    private DevInfoFragment devInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolBar(toolbar);

        appInfoFragment = new AppInfoFragment();
        devInfoFragment = new DevInfoFragment();

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


    private ActionBarDrawerToggle getActionBarDrawerToggle(Toolbar toolbar) {
        return new ActionBarDrawerToggle
                (this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d(TAG, "k");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "c");
            }
        };
    }

    public void setToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = getActionBarDrawerToggle(toolbar);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
}
