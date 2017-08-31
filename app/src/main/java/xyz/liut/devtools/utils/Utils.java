package xyz.liut.devtools.utils;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils
 * <p>
 * Created by Liut2 on 2017/8/25.
 */
public class Utils {

    private static final String TAG = "Utils";

    /**
     * @param context
     * @return 无障碍是否已开启
     */
    public static boolean isAccessibilitySettingsOn(Context context, Class<? extends AccessibilityService> cls) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i(TAG, e.getMessage());
        }
        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure
                    .ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(cls.getName().toLowerCase());
            }
        }
        return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static String DateToString(Date date) {
        return new SimpleDateFormat("MM-dd hh-mm-ss").format(date);
    }
}
