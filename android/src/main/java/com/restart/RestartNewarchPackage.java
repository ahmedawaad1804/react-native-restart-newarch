package com.restartnewarch;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.TurboReactPackage;
import java.util.HashMap;
import java.util.Map;

public class RestartNewarchPackage extends TurboReactPackage {

    @Override
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        if (name.equals(RestartModuleImpl.NAME)) {
            return new RestartModule(reactContext);
        }
        return null;
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return () -> {
            String moduleName = RestartModuleImpl.NAME;
            boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;

            Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
            moduleInfos.put(moduleName, new ReactModuleInfo(
              moduleName,
              moduleName,
              false, // canOverrideExistingModule
                      false, // needsEagerInit
                      true, // hasConstants
                      false, // isCxxModule
              isTurboModule    // isTurboModule  // isTurboModule
            ));
            return moduleInfos;
        };
    }
}
