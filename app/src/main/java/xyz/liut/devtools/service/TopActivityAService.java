package xyz.liut.devtools.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.liut.devtools.R;

/**
 * 无障碍服务，用于获取当前界面类
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public class TopActivityAService extends AccessibilityService {

    private static final String TAG = "TopActivityAService";
    public static final String IS_OPEN = "is_open";

    @BindView(R.id.tv_pkg_name)
    TextView tvPkgName;
    @BindView(R.id.tv_cls_name)
    TextView tvClsName;

    private WindowManager.LayoutParams params;
    private WindowManager wm;
    private View fView;

    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        super.onCreate();
        initParams();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        fView = LayoutInflater.from(this).inflate(R.layout.floating_top_activity, null);
        ButterKnife.bind(this, fView);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean topActivityIsOpen = intent.getBooleanExtra(IS_OPEN, true);
        try {
            if (topActivityIsOpen)
                wm.addView(fView, params);
            else
                wm.removeViewImmediate(fView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return;

        String pkgName = String.valueOf(event.getPackageName());
        String clsName = String.valueOf(event.getClassName());

        Log.i(TAG, pkgName);
        Log.i(TAG, clsName);

        tvPkgName.setText(pkgName);
        tvClsName.setText(clsName);

        try {
            wm.updateViewLayout(fView, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_cls_name, R.id.tv_pkg_name})
    public void onClick(View v) {
        Toast.makeText(this, "test...", Toast.LENGTH_SHORT).show();
    }

    private void initParams() {
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_PHONE; // 类型
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSLUCENT; // 不设置这个弹出框的透明遮罩显示为黑色
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.START | Gravity.TOP;
    }

    @Override
    public void onInterrupt() {
        try {
            wm.removeViewImmediate(fView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        try {
            wm.removeViewImmediate(fView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onUnbind(intent);
    }
}
