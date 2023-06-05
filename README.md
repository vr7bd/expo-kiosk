# expo-kiosk

Module that ensure android apps run in kiosk mode and has functions to exit out of kiosk mode

# API documentation

The module only works for android. It is recommended to not exit apps on iOS and will be considered as a crash. If you would like to run your app in kiosk mode for iOS, refer to [Guided Access](https://support.apple.com/en-in/HT202612)
. The module by default makes sure the app runs in fullscreen.

There are 2 functions that can be called from the repo:-

`exitApp()` - This function does not kill the app. It allows the user to select the Home app.

`killApp()` - This function kills the app. Make sure there is no logic below this function as the app is getting killed

The above functions can be used in combination.

# Help

Running in fullscreen does not work for all android versions. I tried to make it work but couldn't. If you have found a way, kindly submit a PR.

# Installation in managed Expo projects

For [managed](https://docs.expo.dev/archive/managed-vs-bare/) Expo projects, please follow the installation instructions in the [API documentation for the latest stable release](#api-documentation). If you follow the link and there is no documentation available then this library is not yet usable within managed projects &mdash; it is likely to be included in an upcoming Expo SDK release.

# Installation in bare React Native projects

For bare React Native projects, you must ensure that you have [installed and configured the `expo` package](https://docs.expo.dev/bare/installing-expo-modules/) before continuing.

### Add the package to your npm dependencies

```
npm install expo-kiosk
```

### Configure for Android

No additional setup necessary.

# Contributing

Contributions are very welcome! Please refer to guidelines described in the [contributing guide](https://github.com/expo/expo#contributing).
