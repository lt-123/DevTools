package xyz.liut.devtools.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * DevTools
 * </p>
 * Created by liut on 2017/10/14.
 */
public class SharedPreferencesUtil {

    private static final String ISFIRST = "isFirst";

    public static boolean isFirst(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getBoolean(ISFIRST, true);
    }

    public static void setFirst(Context context, boolean isFirst) {
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(ISFIRST, isFirst)
                .apply();
    }


}
