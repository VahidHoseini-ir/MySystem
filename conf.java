package ir.vahidhoseini.callrecorder;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSInAppMessageAction;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class conf extends Application {
    public static Context context;
    public static Activity currentActivity;
    public static boolean ActivatingExAds = false;
    public static boolean ActivatingMyAds = false;
    public static boolean ActivatingGlobalAd = false;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

}
