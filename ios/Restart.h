#ifdef RCT_NEW_ARCH_ENABLED
#import <RNRestartSpec/RNRestartSpec.h>
@interface Restart : NSObject <NativeRestartSpec>
#else
#import <React/RCTBridgeModule.h>
@interface Restart : NSObject <RCTBridgeModule>
#endif

@end
