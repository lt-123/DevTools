package xyz.liut.devtools.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import xyz.liut.devtools.utils.Utils;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 *
 * @author justsy
 */
class CrashHandler implements UncaughtExceptionHandler {

    private static final String LOG_TAG = "DevTool crash";

    private Context context;

    private UncaughtExceptionHandler defaultHandler;

    @SuppressLint("StaticFieldLeak")
    private static CrashHandler instance;

    private Map<String, String> map = new LinkedHashMap<>();


    //路径
    private String BASE_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "devTools" +
            File.separator;
    private String CRASH_FILE_PATH = BASE_FILE_PATH + "Crash";


    /**
     * 单例模式
     *
     * @return crash实例
     */
    static CrashHandler getInstance() {
        if (instance == null)
            instance = new CrashHandler();
        return instance;
    }

    /**
     * 初始化
     */
    void init(Context context) {
        this.context = context;
        // 获取系统默认的UncaughtException处理器
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
        defaultHandler.uncaughtException(thread, ex);
    }

    /**
     * 收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 异常
     */
    private void handleException(Throwable ex) {
        // 收集信息 
        collectDeviceInfo();

        //收集app信息
        collectAppInfo();

        // 保存日志文件 
        saveCrashFile(ex);
    }

    /**
     * 收集设备信息
     */
    private void collectDeviceInfo() {
        try {
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(null).toString());
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "collect crash info Exception", e);
        }
    }

    /**
     * 收集应用信息
     */
    private void collectAppInfo() {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                map.put("versionName", (pi.versionName != null ? pi.versionName : "is null"));
                map.put("versionCode", String.valueOf(pi.versionCode));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "collect package info Exception", e);
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常
     */
    private void saveCrashFile(Throwable ex) {
        PrintWriter pw = null;
        FileOutputStream fos = null;

        StringBuilder sb = new StringBuilder();
        try {
            // 设备、应用信息
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("\n");
            }

            // 错误信息
            Writer writer = new StringWriter();
            pw = new PrintWriter(writer);
            ex.printStackTrace(pw);

            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(pw);
                cause = cause.getCause();
            }
            sb.append(writer.toString());

            File dir = new File(CRASH_FILE_PATH);
            if (!dir.exists()) //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();

            String fileName = "dt-crash-" + Utils.DateToString(new Date()) + ".txt";
            fos = new FileOutputStream(CRASH_FILE_PATH + File.separator + fileName);
            fos.write(sb.toString().getBytes());
        } catch (Exception e) {
            Log.e(LOG_TAG, "writing file Exception", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

}
