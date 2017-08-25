package xyz.liut.devtools.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

/**
 * Utils
 * <p>
 * Created by Liut2 on 2017/8/25.
 */
public class Utils {

    /**
     * @param context
     * @return 无障碍是否已开启
     */
    public static boolean isAccessibilitySettingsOn(Context context, Class<? extends AccessibilityService> cls) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context
                .getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> accessibilityServices =
                accessibilityManager.getEnabledAccessibilityServiceList(
                        AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().contains(cls.getName())) {
                return true;
            }
        }
        return false;
    }


}
