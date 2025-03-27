<p align="center"><img src="https://github.com/MubarakNative/WikiNewsFeed/blob/main/WikiNews_Banner.png" width="700"></p>
<h1 align="center"><b>📰 WikiNewsFeed</b></h1>
<h4 align="center">A modern news application for Android developed using Jetpack Compose, based on Wikinews.</h4>
<p align="center">
    <a href="https://www.gnu.org/licenses/gpl-3.0">
        <img src="https://img.shields.io/badge/license-GPL%20v3-2B6DBE.svg?style=flat">
    </a>
    <a href="https://github.com/MubarakNative/MBCompass/releases">
        <img alt="Downloads" src="https://img.shields.io/badge/Status-Development-1673A8?style=flat">
    </a>
<a href="https://apilevels.com/">
    <img alt="Minimum SDK Version" src="https://img.shields.io/badge/API-21%2B-1450A8?style=flat">
  </a>
</p>

## About

WikiNews is a sleek and modern Android news application built with the latest technologies. It uses the [Wikimedia API](https://api.wikimedia.org/wiki/Main_Page) for news data.

## Screenshots

![](/WikiNews_Screenshots.png)
<p align="center">
    <img src="/WikiNewsAppDemo.gif" >
</p>

## Tech Stack

- 🎨 Jetpack Compose: Android Modern UI framework.
- 🌐 Ktor: A lightweight HTTP client for making requests.
- 🛠️ Hilt: A dependency injection framework.
- 🖼️ Coil Compose: A library for loading and displaying images in Jetpack Compose.
- Navigation suite scaffold for dynamic navigation layout.
- Kotlin Coroutines and flow
- 🏗️ Modern Android Architecture: Following best practices for app architecture and design patterns

## Features

- [x] Get latest News article retrieval from [WikiMedia API]("https://api.wikimedia.org/wiki/Main_Page")
- [x] Multiple news categories like featured, mostread and onthisday.
- [x] Search for the latest and entire list of news items in WikiMedia API.
- [x] Display news in details within the app.
- [x] Card-based layout.
- [x] Supports different screen sizes with dynamic navigation layout and news items.
- [ ] Offline news caching.
- [ ] Settings screen for theme changing.
- [ ] Bookmarking news for later reads.

## Improvements

> Note that this project is still in the development phase.

As you might ask me
> Where was the app released?

Currently, you can't download this app because it is in development, but there is a hardcoded testing api key that can be used for testing purposes

  ---
For more about:

User Authentication, please check out: https://api.wikimedia.org/wiki/Authentication#User_authentication

API Rate limit, please check out: https://api.wikimedia.org/wiki/Rate_limits

## License

[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

WikiNewsFeed is licensed under GPLv3

Read the full license text [here](https://github.com/MubarakNative/WikiNewsFeed/blob/main/LICENSE).
