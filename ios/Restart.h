#ifdef RCT_NEW_ARCH_ENABLED
#import <RNRestartSpec/RNRestartSpec.h>
@interface RestartNewArch : NSObject <NativeRestartSpec>
#else
#import <React/RCTBridgeModule.h>
@interface RestartNewArch : NSObject <RCTBridgeModule>
#endif

@end
