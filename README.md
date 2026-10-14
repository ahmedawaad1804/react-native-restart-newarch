# ⚡ React Native Restart (New & Old Arch ) 🚀

[![npm version](https://img.shields.io/npm/v/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![npm downloads](https://img.shields.io/npm/dm/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![Build Status](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions/workflows/ci.yml/badge.svg)](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions)
[![License](https://img.shields.io/npm/l/react-native-restart-newarch.svg)](./LICENSE)
[![Bundlephobia](https://img.shields.io/bundlephobia/minzip/react-native-restart-newarch.svg)](https://bundlephobia.com/package/react-native-restart-newarch)
[![Platform](https://img.shields.io/badge/platform-react--native-blue.svg)](https://reactnative.dev)
[![Architecture](https://img.shields.io/badge/Supports-New%20%26%20Old%20Arch-success.svg)](https://reactnative.dev/architecture/)

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

(Yeah, it's that easy!)

---

## 🚀 Usage

```js
import RNRestart from "react-native-restart-newarch"; // Import package from node modules

// Restart the app (see platform behavior below)

RNRestart.restart();
// Or
RNRestart.Restart();
```

### Platform behavior

- **iOS** reloads React Native through its reload command listeners, after a short delay. Requests during that delay are coalesced.
- **Android** starts the launcher activity in a fresh task and exits the current process.
- **Web** reloads the current page.

Persist any state you need before calling `restart()`. This API returns `void`; it does not signal when the restart has completed.

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
