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


    WindowManager.LayoutParams params = new WindowManager.LayoutParams();

    @BindView(R.id.tv_pkg_name)
    TextView tvPkgName;
    @BindView(R.id.tv_cls_name)
    TextView tvClsName;

    private WindowManager wm;
    private View fView;

    @SuppressLint("InflateParams")
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        initParams();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        fView = LayoutInflater.from(this).inflate(R.layout.floating_top_activity, null);
        ButterKnife.bind(this, fView);

        try {
            wm.addView(fView, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.tv_cls_name, R.id.tv_pkg_name})
    public void onClick(View v) {
        Toast.makeText(this, "test...", Toast.LENGTH_SHORT).show();
    }

    private void initParams() {
//            int w, int h, int _type, int _flags, int _format
//            params = new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT <= 24 ? 2005 : 2002, 24, -3);
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_PHONE;

        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

        // 设置flag
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // FLAG_NOT_TOUCH_MODAL 不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题

        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.START | Gravity.TOP;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

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

    @Override
    public void onInterrupt() {
        wm.removeViewImmediate(fView);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        wm.removeViewImmediate(fView);
        return super.onUnbind(intent);
    }
}
