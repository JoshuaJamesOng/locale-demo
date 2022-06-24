# Locale Demo

Showcases AppCompat's backport of [per-app language preferences](https://developer.android.com/about/versions/13/features/app-languages)

![screenshot-1656066119485](https://user-images.githubusercontent.com/3862718/175517671-cf21b084-1225-4b3f-b13e-4dd6ba391212.png)

The app consists of:
* A toolbar title
* A localised `TextView` that matches the configuration's `Locale` i.e. `strings.xml`
* A set of buttons to trigger language changes
* A set of `TextView` formatting using `Locale.getDefault`

Running on API 12 or lower has a few edge cases to cover:
* Toolbar titles are cached. Thus even when the Locale of the Configuration changes, the title is in an old Locale. We need to bust the cache on `Activity` creation
* Changing the language in the OS settings causes a configuration change for the `Application`, overwriting `Locale#getDefault`'s value'. We have to listen to component changes and restore the value.

## Cases tested
* Changing language and resources in-app.
* Re-launching process and ensuring preference is restored.
* Backgrounding the app, changing the OS language and foregrounding the app, ensuring preference is maintained.

## 12/24-hour preference
One note is toggling the preference when the app's already launched yields no change; times continue to print in the previous format. This behaviour suggests it's not something strictly derived from the `Locale` on Android 7. From Android 9 onwards, there's a third option in OS settings to respect the `Locale` rules, which gives us behaviour we might expect; 24 hours for the UK whilst 12-hour for the US.
