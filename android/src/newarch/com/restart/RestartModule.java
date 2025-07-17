package com.restartnewarch;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.UiThreadUtil;
import androidx.annotation.NonNull;


public class RestartModule extends NativeRestartSpec {



    private final ReactApplicationContext reactContext;

    public RestartModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return RestartModuleImpl.NAME;

    }

    @Override
    public void restart() {
        RestartModuleImpl.restartApp(reactContext);
    }

}
