# TyanGram

**TyanGram** is an Android Telegram client mod focused on privacy and lower resource usage.  
Based on the official [Telegram for Android](https://github.com/DrKLO/Telegram) source.

## Goals
- Remove tracking/telemetry and unnecessary Google/Firebase integrations
- Reduce memory and storage footprint where possible
- Keep core messaging functionality

## What has been stripped so far (initial pass)
- Removed **HockeyApp** app module and build targets
- Removed **Firebase/Crashlytics** Gradle plugins, BoM and dependencies (messaging, PNV, app indexing)
- Removed **google-services** plugin/config and all `google-services.json` files
- Deleted Firebase/GCM-specific classes: `FirebasePhoneNumberVerificationHelper`, `GcmPushListenerService`

> **Status:** work in progress. This is an early cleanup pass — remaining references to removed services (manifests, call sites, Huawei push specifics) still need to be refactored for a clean, fully buildable APK. Contributions and testing help wanted.

## Building
Requires Android SDK/NDK, and Telegram API credentials (`APP_ID`/`APP_HASH` from https://my.telegram.org). See upstream Telegram build instructions as a starting point.

## License
TyanGram is based on Telegram for Android and is distributed under the **GNU GPL v.2 or later** (see `LICENSE`).
Telegram is a trademark of Telegram FZ-LLC. This project is not affiliated with or endorsed by Telegram.
