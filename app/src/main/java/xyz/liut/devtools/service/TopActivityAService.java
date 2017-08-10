package xyz.liut.devtools.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * 无障碍服务，用于获取当前界面类
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public class TopActivityAService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}
