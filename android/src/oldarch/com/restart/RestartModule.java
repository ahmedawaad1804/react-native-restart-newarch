package com.restart;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RestartModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RestartModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return RestartModuleImpl.NAME;
    }

    @ReactMethod
    public void restart() {
        RestartModuleImpl.restartApp(reactContext);
    }
}
