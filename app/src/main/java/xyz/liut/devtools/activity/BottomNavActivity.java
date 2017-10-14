package xyz.liut.devtools.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeHelper;

import xyz.liut.devtools.MyWelcomeActivity;
import xyz.liut.devtools.R;
import xyz.liut.devtools.fragment.AppInfoFragment;
import xyz.liut.devtools.fragment.DevInfoFragment;
import xyz.liut.devtools.fragment.LittleToolsFragment;
import xyz.liut.devtools.utils.SharedPreferencesUtil;

/**
 * 底部导航
 * <p>
 * Created by Liut2 on 2017/8/29.
 */
public class BottomNavActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_WELCOME_SCREEN_RESULT = 1000;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10001;
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
        fm.beginTransaction().replace(R.id.content, appInfoFragment).commit();

        boolean isFirst = SharedPreferencesUtil.isFirst(getApplicationContext());
        if (isFirst)
            new WelcomeHelper(this, MyWelcomeActivity.class).forceShow(REQUEST_WELCOME_SCREEN_RESULT);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        PermissionChecker.checkPermission()
//        PermissionUtil.checkPermission(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fm.beginTransaction()
                        .replace(R.id.content, appInfoFragment).commit();
                return true;
            case R.id.navigation_dashboard:
                fm.beginTransaction()
                        .replace(R.id.content, devInfoFragment).commit();
                return true;
            case R.id.navigation_notifications:
                fm.beginTransaction()
                        .replace(R.id.content, littleToolsFragment).commit();
                return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_WELCOME_SCREEN_RESULT:
                if (resultCode == RESULT_OK) {
                    SharedPreferencesUtil.setFirst(getApplicationContext(), false);
                    Toast.makeText(getApplicationContext(), "Completed (RESULT_OK)", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Canceled (RESULT_CANCELED)", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}
