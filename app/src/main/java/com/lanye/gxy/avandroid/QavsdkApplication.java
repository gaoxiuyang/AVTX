package com.lanye.gxy.avandroid;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.lanye.gxy.avandroid.presenters.InitBusinessHelper;
import com.lanye.gxy.avandroid.utils.SxbLogImpl;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.rtmp.ITXLiveBaseListener;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePusher;

/**
 * 全局Application
 */
public class QavsdkApplication extends Application implements ITXLiveBaseListener {

    private static QavsdkApplication app;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        context = getApplicationContext();
        TXLiveBase.getInstance().listener = this;
        SxbLogImpl.init(getApplicationContext());
        //初始化APP
       InitBusinessHelper.initApp(context);
//
//
//
        int[] sdkVer = TXLivePusher.getSDKVersion(); //这里调用TXLivePlayer.getSDKVersion()也是可以的
//        if (sdkVer != null && sdkVer.length >= 3) {
//            if (sdkVer[0] > 0 && sdkVer[1] > 0) {
//                //启动bugly组件，bugly组件为腾讯提供的用于crash上报和分析的开放组件，如果您不需要该组件，可以自行移除
//                CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//                strategy.setAppVersion(String.format("%d.%d.%d",sdkVer[0],sdkVer[1],sdkVer[2]));
//                CrashReport.initCrashReport(getApplicationContext(),strategy);
//
//                Log.d("rtmpsdk","init crash report");
//            }
//        }
















//        LeakCanary.install(this);

        //创建AVSDK 控制器类
    }

    public static Context getContext() {
        return context;
    }

    public static QavsdkApplication getInstance() {
        return app;
    }

    @Override
    public void OnLog(int level, String module, String log) {
        switch (level)
        {
            case TXLiveConstants.LOG_LEVEL_ERROR:
                Log.e(module, log);
                break;
            case TXLiveConstants.LOG_LEVEL_WARN:
                Log.w(module, log);
                break;
            case TXLiveConstants.LOG_LEVEL_INFO:
                Log.i(module, log);
                break;
            case TXLiveConstants.LOG_LEVEL_DEBUG:
                Log.d(module, log);
                break;
            default:
                Log.d(module, log);
        }
    }
}
