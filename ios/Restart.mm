#import "Restart.h"
#import <React/RCTReloadCommand.h>

@implementation RestartNewArch

RCT_EXPORT_MODULE()

static BOOL reloadScheduled = NO;

- (void)loadBundle
{
    RCTTriggerReloadCommandListeners(@"react-native-restart-newarch: RestartNewArch");
}

RCT_EXPORT_METHOD(restart)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        if (reloadScheduled) {
            return;
        }

        reloadScheduled = YES;
        [self loadBundle];
    });
}

#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRestartSpecJSI>(params);
}
#endif

@end