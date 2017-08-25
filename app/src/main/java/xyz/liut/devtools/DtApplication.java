package xyz.liut.devtools;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * 应用 Application
 * <p>
 * Created by Liut2 on 2017/8/16.
 */
public class DtApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 安装LeakCanary
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }

        // 初始化 Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i("test");

    }

}
