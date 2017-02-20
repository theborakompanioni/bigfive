# Install

## Android SDK
[Install Android Studio] (https://developer.android.com/studio/install.html)

##  Ionic
```
$ sudo npm install -g ionic cordova
```

# Development
```
$ ionic platform add android
$ ionic serve -t android
```

# Build
```
$ ionic build android
```

# Run 
Create a new Android Virtual Device (with gui)
```
$ android avd
```

Start emulator
```
$ ionic emulate --target=<avd-name> android
```

or

```
emulator -list-avds
emulator -avd <your-avd-name> -wipe-data -no-boot-anim
adb install platforms/android/build/outputs/apk/android-debug.apk
```


# FAQ
## [How do you install an APK file in the Android emulator?](http://stackoverflow.com/questions/3480201/how-do-you-install-an-apk-file-in-the-android-emulator)
## [Ionic run does nothing](http://stackoverflow.com/questions/33493015/ionic-run-does-nothing)
possible solution: downgrade node to v4.2.1
```
$ nvm install 4.2.1 && nvm use 4.2.1
```

