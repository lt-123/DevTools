package xyz.liut.devtools;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 应用 Application
 * <p>
 * Created by Liut2 on 2017/8/16.
 */
public class DtApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i("test");
    }
}
