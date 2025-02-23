package com.restart;

import android.content.Intent;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;

public class RestartModuleImpl {

    public static final String NAME = "Restart";

    public static void restartApp(ReactApplicationContext reactContext) {
        try {
            Intent intent = reactContext.getPackageManager().getLaunchIntentForPackage(reactContext.getPackageName());

            if (intent != null) {
                Intent mainIntent = Intent.makeRestartActivityTask(intent.getComponent());
                reactContext.startActivity(mainIntent);
                Runtime.getRuntime().exit(0);
            } else {
                Log.e(NAME, "Launch intent not found for package: " + reactContext.getPackageName());
            }
        } catch (Exception e) {
            Log.e(NAME, "Error restarting app", e);
        }
    }
}
