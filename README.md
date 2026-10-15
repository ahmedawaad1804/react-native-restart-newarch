# React Native Restart

[![npm version](https://img.shields.io/npm/v/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![npm downloads](https://img.shields.io/npm/dm/react-native-restart-newarch.svg)](https://www.npmjs.com/package/react-native-restart-newarch)
[![Build Status](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions/workflows/ci.yml/badge.svg)](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions)
[![License](https://img.shields.io/npm/l/react-native-restart-newarch.svg)](./LICENSE)
[![Platforms: Android, iOS, Web](https://img.shields.io/badge/platforms-Android%20%7C%20iOS%20%7C%20Web-blue.svg)](#platform-behavior)
[![Architecture](https://img.shields.io/badge/Supports-New%20%26%20Old%20Arch-success.svg)](https://reactnative.dev/architecture/)

Restart your Android app, reload React Native on iOS, or reload the current page on web. Includes support for New and Old Architecture and TypeScript declarations, with no runtime dependencies.

[Installation](#installation) · [Usage](#usage) · [API](#api) · [Compatibility](#compatibility) · [Troubleshooting](#troubleshooting) · [Release notes](https://github.com/ahmedawaad1804/react-native-restart-newarch/blob/main/CHANGELOG.md)

## Compatibility

The example uses React Native **0.78.0**. Build verification and runtime restart verification are separate:

| React Native   | Platform      | Architecture    | Build verification       | Native restart smoke test |
| -------------- | ------------- | --------------- | ------------------------ | ------------------------- |
| 0.78.0         | Android       | New             | Debug arm64 build passed | Not yet recorded          |
| 0.78.0         | Android       | Old             | Debug arm64 build passed | Not yet recorded          |
| 0.78.0         | iOS           | New             | Simulator build passed   | Not yet recorded          |
| 0.78.0         | iOS           | Old             | Not verified             | Not verified              |
| 0.88           | Android / iOS | New             | Not verified             | Not verified              |
| Other versions | Android / iOS | Where available | Not verified             | Not verified              |

Web has a page-reload implementation; browser runtime verification is not recorded in this table. See the [CI workflow](https://github.com/ahmedawaad1804/react-native-restart-newarch/actions/workflows/ci.yml) and [native smoke-test procedure](CONTRIBUTING.md#native-restart-smoke-test).

The npm peer dependency range (`react-native >=0.60.0`) is an installation constraint retained for existing consumers, **not a verified support promise**. A supported minimum has not yet been established through native builds and restart tests across older versions. Do not interpret an accepted installation as confirmed compatibility.

- **iOS:** the minimum required by your React Native version (`min_ios_version_supported`).
- **Android:** API 24 by default; your app may require a higher minimum.
- **Expo:** requires a native development or standalone build; Expo Go is unsupported.

## Installation

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

## Usage

```tsx
import { Button } from "react-native";
import { restart } from "react-native-restart-newarch";

export function RestartButton() {
  return <Button title="Restart app" onPress={restart} />;
}
```

The default import also supports `RNRestart.restart()` and the equivalent `RNRestart.Restart()` alias. Call one of these from an event handler. Do not call restart at module scope, during render, or in an unconditional startup effect: that can create a restart loop.

When saving settings, await the storage operation before restarting:

```ts
import { restart } from "react-native-restart-newarch";

async function saveAndRestart(saveChanges: () => Promise<void>) {
  await saveChanges(); // Your app's persistence operation; let failures reach the caller.
  restart();
}
```

Call `saveAndRestart` from your settings handler and handle save errors there. Awaiting `restart()` itself does not wait for the app to restart.

## API

```ts
import RNRestart, { restart, Restart } from "react-native-restart-newarch";
```

| API                   | Parameters | Returns | Behavior                             |
| --------------------- | ---------- | ------- | ------------------------------------ |
| `restart()`           | None       | `void`  | Requests the platform restart/reload |
| `Restart()`           | None       | `void`  | Alias of `restart()`                 |
| `RNRestart.restart()` | None       | `void`  | Default-import equivalent            |
| `RNRestart.Restart()` | None       | `void`  | Default-import alias                 |

There is no completion callback or promise. Persist changes before calling any of these methods.

### Platform behavior

- **iOS** reloads React Native through its reload command listeners, after a short delay. Requests during that delay are coalesced.
- **Android** starts the launcher activity in a fresh task and exits the current process.
- **Web** reloads the current page.

Persist any state you need before calling `restart()`. This API returns `void`; it does not signal when the restart has completed. iOS does not restart the entire process, so native singletons can survive the React Native reload.

For device verification, see the [native restart smoke test](CONTRIBUTING.md#native-restart-smoke-test).

---

## Features

- New and Old Architecture integration; see the compatibility table for verified builds.
- React Native autolinking with no manual native registration in standard projects.
- Named and default imports with included TypeScript declarations.
- No runtime dependencies.
- Useful after persisting app settings or configuration that requires a reload.

## Troubleshooting

| Symptom                                        | What to check                                                                                                                                                        |
| ---------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `RestartNewArch` native module cannot be found | Install iOS pods, then rebuild and reinstall the native app. Restarting Metro alone cannot add a native module. Confirm the dependency belongs to the app workspace. |
| Module missing in Expo Go                      | Use an Expo development build or standalone build that includes the package, then rebuild after installation.                                                        |
| App repeatedly restarts                        | Remove calls from module scope, render, and unconditional startup effects. Trigger from a user action or a condition that is cleared before restart.                 |
| Settings disappear after restart               | Await persistence before calling `restart()`. React component state is intentionally lost.                                                                           |
| Native state remains on iOS                    | iOS reloads React Native rather than terminating the process. Reset native singleton state through that component's own API if needed.                               |
| Debug app cannot load after restart            | Ensure Metro is running and reachable. Also test a Release build with its bundled JavaScript.                                                                        |

For unresolved issues, [open a bug report](https://github.com/ahmedawaad1804/react-native-restart-newarch/issues) with the package and React Native versions, platform/OS, architecture, Debug or Release mode, logs, and a minimal reproduction.

## Releases

See the [changelog](https://github.com/ahmedawaad1804/react-native-restart-newarch/blob/main/CHANGELOG.md) for version-specific changes. Version 1.0.92 was published through GitHub Actions with npm provenance; its source and build attestations are available on the npm page.

## Contributing

We welcome PRs, issues, and ideas! See the [contributing guide](CONTRIBUTING.md) for details.

---

## License

[MIT](./LICENSE)
