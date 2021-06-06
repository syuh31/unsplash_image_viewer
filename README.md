Unsplash Image Viewer
=====================

The architecture of this app imitated [android-architecture GitHubBrowserSample](https://github.com/android/architecture-components-samples/tree/main/GithubBrowserSample), but is created more simple.

This app does not have repository layer.

Introduction
------------

The app is only one screen.

Get photos from unsplash api immediately after launching the app and show images by ImageListFragment.

Using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection.
Dependency is decrared in PhotoListViewApp.kt.

Use LiveData to make the View and ViewModel work together.

Test code is not complete.

Api error handling is not complete.
