# Contributing

Contributions are always welcome, no matter how large or small!

We want this community to be friendly and respectful to each other. Please follow it in all your interactions with the project. Before contributing, please read the [code of conduct](./CODE_OF_CONDUCT.md).

## Development workflow

This project is a monorepo managed using [Yarn workspaces](https://yarnpkg.com/features/workspaces). It contains the following packages:

- The library package in the root directory.
- An example app in the `example/` directory.

To get started with the project, run `yarn` in the root directory to install the required dependencies for each package:

```sh
yarn
```

> Since the project relies on Yarn workspaces, you cannot use [`npm`](https://github.com/npm/cli) for development.

The [example app](/example/) demonstrates usage of the library. You need to run it to test any changes you make.

It is configured to use the local version of the library, so any changes you make to the library's source code will be reflected in the example app. Changes to the library's JavaScript code will be reflected in the example app without a rebuild, but native code changes will require a rebuild of the example app.

If you want to use Android Studio or XCode to edit the native code, you can open the `example/android` or `example/ios` directories respectively in those editors. To edit the Objective-C or Swift files, open `example/ios/RestartExample.xcworkspace` in XCode and find the source files at `Pods > Development Pods > react-native-restart-newarch`.

To edit the Java or Kotlin files, open `example/android` in Android studio and find the source files at `react-native-restart-newarch` under `Android`.

You can use various commands from the root directory to work with the project.

To start the packager:

```sh
yarn example start
```

To run the example app on Android:

```sh
yarn example android
```

To run the example app on iOS:

```sh
yarn example ios
```

To confirm that the app is running with the new architecture, you can check the Metro logs for a message like this:

```sh
Running "RestartExample" with {"fabric":true,"initialProps":{"concurrentRoot":true},"rootTag":1}
```

Note the `"fabric":true` and `"concurrentRoot":true` properties.

Make sure your code passes TypeScript and ESLint. Run the following to verify:

```sh
yarn typecheck
yarn lint
```

To fix formatting errors, run the following:

```sh
yarn lint --fix
```

Remember to add tests for your change if possible. Run the unit tests by:

```sh
yarn test
```

### Native restart smoke test

The Jest tests mock the native module. Use the example app to check the actual native restart path. Its counter deliberately lives only in React state and must reset after a restart.

1. Build and install the current example on a simulator, emulator, or device. For Debug, start Metro with `yarn workspace react-native-restart-example start` and make sure the app can reach it. For Release, use a build with the JavaScript bundle included.
2. Tap **Increment** and confirm **Count: 1**.
3. Tap **Restart app**. The app must return to **Count: 0** without manually relaunching it.
4. Repeat steps 2–3 three times.
5. Increment again, then tap **Request restart three times**. Confirm the app returns to **Count: 0** and still responds to **Increment**.

With [Maestro installed](https://docs.maestro.dev/getting-started/installing-maestro), the same checks can be run from the repository root against an installed example:

```sh
maestro test example/.maestro/restart.yaml
```

The flow launches the app only at the start. It waits for the package's own restart after each button press, so it does not hide a failed restart by relaunching the app itself. See Maestro's [repeat](https://docs.maestro.dev/reference/commands-available/repeat) and [extendedWaitUntil](https://docs.maestro.dev/reference/commands-available/extendedwaituntil) commands.

Run on Android and iOS in both Debug and Release. On RN versions that support both architectures, cover both; use New Architecture for versions that require it. Record the RN version, OS, architecture, build mode, and result. A successful native build alone is not a passing smoke test. The burst check verifies recovery and responsiveness, not the exact number of native reloads.

For a consuming app, also save a real setting through its persistence layer, await the save, restart, and confirm the setting is restored. The example counter is intentionally not persisted and this flow does not validate your app's storage or native singleton reset behavior.

### Commit message convention

We follow the [conventional commits specification](https://www.conventionalcommits.org/en) for our commit messages:

- `fix`: bug fixes, e.g. fix crash due to deprecated method.
- `feat`: new features, e.g. add new method to the module.
- `refactor`: code refactor, e.g. migrate from class components to hooks.
- `docs`: changes into documentation, e.g. add usage example for the module..
- `test`: adding or updating tests, e.g. add integration tests using detox.
- `chore`: tooling changes, e.g. change CI config.

Our pre-commit hooks verify that your commit message matches this format when committing.

### Linting and tests

[ESLint](https://eslint.org/), [Prettier](https://prettier.io/), [TypeScript](https://www.typescriptlang.org/)

We use [TypeScript](https://www.typescriptlang.org/) for type checking, [ESLint](https://eslint.org/) with [Prettier](https://prettier.io/) for linting and formatting the code, and [Jest](https://jestjs.io/) for testing.

Our pre-commit hooks verify that the linter and tests pass when committing.

### Publishing to npm

Use `.github/workflows/publish.yml` to publish from GitHub-hosted runners with npm provenance. This creates the source-commit, workflow, and transparency-log links on the npm package page. Publishing from a local terminal does not generate GitHub build provenance.

One-time npm setup, after the workflow has been pushed to GitHub:

1. Open the npm package's **Settings → Trusted publishing → Add trusted publisher** and select **GitHub Actions**.
2. Set owner to `ahmedawaad1804`, repository to `react-native-restart-newarch`, and workflow filename to `publish.yml` (without its directory).
3. Leave environment blank; this workflow does not use a GitHub environment.
4. Enable **Allow npm publish** for direct publishing. No npm token secret is needed.

Follow the [npm trusted-publishing guide](https://docs.npmjs.com/trusted-publishers/). Newly configured publishers must complete a first publish within npm's activation window (currently two days), so configure this when ready to release.

For each release:

- Move completed entries from `CHANGELOG.md` under the new version. Separate runtime fixes, packaging changes, documentation, and CI changes; do not claim tests that were not run.
- Update the README compatibility table only with recorded build or smoke-test evidence. Before changing the React Native peer minimum, test candidate minimum versions on both native platforms and record the architecture and build mode. Treat a raised minimum as a compatibility change.

Release steps:

1. Update `package.json`, the root version entries in `package-lock.json`, and the example's CocoaPods lockfile using `pod install` from `example/ios`.
2. Commit and push the changes to `main`, and confirm the CI workflow passes for that commit.
3. Open **GitHub Actions → Publish to npm → Run workflow**. Select `main` and enter the exact, unpublished version from `package.json`.
4. Confirm the publish job succeeds, then check the version and provenance on npm.

The workflow uses Node 24 and npm 11, installs dependencies without restoring build caches, builds and checks the package, and publishes using OIDC with `id-token: write`. It rejects a version that does not match the checked-out package and only runs the publishing job on `main`.

An already published version cannot be overwritten or republished to add provenance. Use the next version if the current version was published locally.

### Scripts

The `package.json` file contains various scripts for common tasks:

- `yarn`: setup project by installing dependencies.
- `yarn typecheck`: type-check files with TypeScript.
- `yarn lint`: lint files with ESLint.
- `yarn test`: run unit tests with Jest.
- `yarn example start`: start the Metro server for the example app.
- `yarn example android`: run the example app on Android.
- `yarn example ios`: run the example app on iOS.

### Sending a pull request

> **Working on your first pull request?** You can learn how from this _free_ series: [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github).

When you're sending a pull request:

- Prefer small pull requests focused on one change.
- Verify that linters and tests are passing.
- Review the documentation to make sure it looks good.
- Follow the pull request template when opening a pull request.
- For pull requests that change the API or implementation, discuss with maintainers first by opening an issue.
