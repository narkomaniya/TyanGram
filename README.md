# TyanGram

**TyanGram** is an ultra-lightweight, privacy- and security-focused Telegram client mod for Android.  
Based on the official [Telegram for Android](https://github.com/DrKLO/Telegram) source.

> Built for users who care about privacy, minimal tracking, and a smaller footprint. Actively developed and planned to be updated regularly.

## Key goals
- **Maximum privacy & security focus** — strip tracking/telemetry and reduce unnecessary data exposure wherever possible.
- **Ultra-lightweight** — lower RAM and storage usage by removing bloat and unnecessary components.
- **Clean & minimal** — cut what gets in the way; keep the core messaging experience fast and simple.
- **Ongoing updates** — the project is a work in progress and will continue to improve.

## What has been stripped so far (initial pass)
- Removed **HockeyApp** app module and build targets (crash reporting/distribution)
- Removed **Firebase/Crashlytics** Gradle plugins, BoM and dependencies (messaging, phone-number verification, app indexing)
- Removed **google-services** plugin/config and all `google-services.json` files
- Deleted Firebase/GCM-specific classes: `FirebasePhoneNumberVerificationHelper`, `GcmPushListenerService`

## Status
Early work-in-progress. This is a first cleanup pass — remaining references to removed services (manifests, call sites, Huawei push specifics, generated tests/schemes) still need refactoring for a clean, fully buildable APK. More privacy hardening and footprint reductions are planned.

## Building
Requires Android SDK/NDK and Telegram API credentials (`APP_ID`/`APP_HASH` from https://my.telegram.org). See upstream Telegram build instructions as a starting point.

## Disclaimer
TyanGram is an independent mod, **not affiliated with or endorsed by Telegram**. Use responsibly and in accordance with applicable laws and Telegram’s terms.

## License
Based on Telegram for Android and distributed under the **GNU GPL v.2 or later** (see `LICENSE`). Telegram is a trademark of Telegram FZ-LLC.
