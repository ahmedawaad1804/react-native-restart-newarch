#import "Restart.h"
#import <React/RCTReloadCommand.h>

static BOOL RNRestartReloadScheduled = NO;

#if DEBUG
static const NSTimeInterval RNRestartReloadDelay = 0.5;
#else
static const NSTimeInterval RNRestartReloadDelay = 0.25;
#endif

@implementation RestartNewArch

RCT_EXPORT_MODULE()

- (void)loadBundle
{
    RCTTriggerReloadCommandListeners(@"react-native-restart-newarch: RestartNewArch");
}

RCT_EXPORT_METHOD(restart)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        // Prevent multiple restart requests while one is already pending.
        if (RNRestartReloadScheduled) {
            return;
        }

        RNRestartReloadScheduled = YES;

        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW,
                          (int64_t)(RNRestartReloadDelay * NSEC_PER_SEC)),
            dispatch_get_main_queue(),
            ^{
                RNRestartReloadScheduled = NO;
                [self loadBundle];
            });
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