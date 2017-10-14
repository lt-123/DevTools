package xyz.liut.devtools.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.content.ContextCompat;

/**
 * DevTools
 * </p>
 * Created by liut on 2017/10/14.
 */
public class PermissionUtil {

    public static void checkPermission(Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_CALENDAR);
    }

}
