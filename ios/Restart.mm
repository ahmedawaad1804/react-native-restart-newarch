#import "Restart.h"
#import <React/RCTReloadCommand.h>

@implementation RestartNewArch
RCT_EXPORT_MODULE()

- (void)loadBundle
{
    RCTTriggerReloadCommandListeners(@"react-native-restart-newarch: RestartNewArch");
}

RCT_EXPORT_METHOD(restart) {
    if ([NSThread isMainThread]) {
        [self loadBundle];
    } else {
        dispatch_sync(dispatch_get_main_queue(), ^{
            [self loadBundle];
        });
    }
    return;
}

#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRestartSpecJSI>(params);
}
#endif

@end
