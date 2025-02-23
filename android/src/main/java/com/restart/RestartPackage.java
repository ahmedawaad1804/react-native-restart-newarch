package com.restart;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.HashMap;
import java.util.Map;

public class RestartPackage extends BaseReactPackage {

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
                    false,  // canOverrideExistingModule
                    false,  // needsEagerInit
                    false,  // isCxxModule
                    isTurboModule    // isTurboModule
            ));
            return moduleInfos;
        };
    }
}
