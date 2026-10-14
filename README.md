# ⚡ React Native Restart (New & Old Arch ) 🚀

[![npm version](https://img.shields.io/npm/v/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![npm downloads](https://img.shields.io/npm/dm/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![Build Status](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions/workflows/ci.yml/badge.svg)](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions)
[![License](https://img.shields.io/npm/l/react-native-restart-newarch.svg)](./LICENSE)
[![Bundlephobia](https://img.shields.io/bundlephobia/minzip/react-native-restart-newarch.svg)](https://bundlephobia.com/package/react-native-restart-newarch)
[![Platforms: Android, iOS, Web](https://img.shields.io/badge/platforms-Android%20%7C%20iOS%20%7C%20Web-blue.svg)](#platform-behavior)
[![TypeScript declarations included](https://img.shields.io/badge/TypeScript-types%20included-3178C6.svg?logo=typescript&logoColor=white)](./src/types.tsx)
[![Architecture](https://img.shields.io/badge/Supports-New%20%26%20Old%20Arch-success.svg)](https://reactnative.dev/architecture/)
[![GitHub stars](https://img.shields.io/github/stars/ahmedawaad1804/react-native-restart-newarch.svg?style=flat)](https://github.com/ahmedawaad1804/react-native-restart-newarch/stargazers)
[![Last commit](https://img.shields.io/github/last-commit/ahmedawaad1804/react-native-restart-newarch/main.svg)](https://github.com/ahmedawaad1804/react-native-restart-newarch/commits/main)

Ever wished your React Native app could **restart itself like a phoenix rising from the ashes**? 🔥 **Now it can!**

---

## 📱 Supported Minimum Versions

- **iOS**: the minimum required by your React Native version (`min_ios_version_supported`).
- **Android**: API 24 by default; the host app may require a higher minimum.
- **React Native**: the example uses 0.78.0. The broad npm peer range is not a verified compatibility matrix; older versions require validation.

No more manually killing the app. Just call `RNRestart.restart()` and let the magic happen! 🪄✨

---

## 🛠️ Installation

```sh
npm install react-native-restart-newarch
```

For iOS, install the native pods from your app's `ios` directory:

```sh
cd ios
bundle exec pod install
cd ..
```

If your project does not use Bundler, run `pod install` instead. Rebuild and reinstall the Android or iOS app after installation; restarting Metro alone does not add the native module. React Native autolinking handles registration.

**Expo:** use a development build or a standalone native build that includes this package. It cannot run in Expo Go. After adding it, rebuild your development client. See [Expo's native-library guide](https://docs.expo.dev/workflow/customizing/).

If importing the package reports that `RestartNewArch` could not be found, check that pods were installed and that you are running the rebuilt app.

---

## 🚀 Usage

```tsx
import { Button } from 'react-native';
import { restart } from 'react-native-restart-newarch';

export function RestartButton() {
  return <Button title="Restart app" onPress={restart} />;
}
```

The default import also supports `RNRestart.restart()` and the equivalent `RNRestart.Restart()` alias. Call one of these from an event handler. Do not call restart at module scope, during render, or in an unconditional startup effect: that can create a restart loop.

When saving settings, await the storage operation before restarting:

```ts
import { restart } from 'react-native-restart-newarch';

async function saveAndRestart(saveChanges: () => Promise<void>) {
  await saveChanges(); // Your app's persistence operation; let failures reach the caller.
  restart();
}
```

Call `saveAndRestart` from your settings handler and handle save errors there. Awaiting `restart()` itself does not wait for the app to restart.

### Platform behavior

- **iOS** reloads React Native through its reload command listeners, after a short delay. Requests during that delay are coalesced.
- **Android** starts the launcher activity in a fresh task and exits the current process.
- **Web** reloads the current page.

Persist any state you need before calling `restart()`. This API returns `void`; it does not signal when the restart has completed. iOS does not restart the entire process, so native singletons can survive the React Native reload.

For device verification, see the [native restart smoke test](CONTRIBUTING.md#native-restart-smoke-test).

---

## 🧐 Why Use This?

✅ **Supports the New Architecture** (TurboModules FTW!)
✅ **Supports the Old Architecture** (Backward Compatible)
✅ **No Native Code Tweaks Needed** (Plug & Play)
✅ **Programmatic Restart** (Goodbye, manual reloading)
✅ **Perfect for Feature Toggles** (Change configs & restart on the fly!)

---

## 🤝 Contributing

We welcome PRs, issues, and ideas! See the [contributing guide](CONTRIBUTING.md) for details.

---

## 📜 License

MIT
