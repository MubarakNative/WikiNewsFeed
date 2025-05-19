# 📰 WikiNews: A Modern Android News Client

![](/WikiNews_Banner.png)

## Overview
**WikiNews** is a sleek and modern Android news application built with the latest technologies.

## Technologies Used
- 🎨 **Jetpack Compose**: A modern UI framework for building native Android applications.
- 🌐 **Ktor**: A lightweight HTTP client for making requests to the MediaWiki News API (WikiNews).
- 🛠️ **Hilt**: A dependency injection framework for managing app dependencies.
- 🖼️ **Coil Compose**: A library for loading and displaying images in Jetpack Compose.
- 🏗️ **Modern Android Architecture**: Following best practices for app architecture and design patterns.

## WikiNews API
- ✅ **Reliable and unbiased news**: WikiNews is a project of the Wikimedia Foundation, ensuring neutrality and accuracy.
- ✍️ **Free editing**: Anyone can contribute and edit news articles, promoting transparency and community involvement.
- 💻 **Open-source**: The WikiNews API is freely available for use, allowing developers to build innovative applications.
- 🌍 **Global coverage**: WikiNews provides news from around the world, covering diverse topics and events.
- 🔓 **CC-BY license**: News content is licensed under Creative Commons Attribution, allowing for free reuse and sharing.

### Rate Limits
Rate limits restrict API calls to a set number of requests per hour based on the type of request. A **429** response code indicates that the applicable rate limit has been exceeded.

These limits apply only to APIs with **api.wikimedia.org** as the base URL. Rate limits may vary depending on the specific API. For higher rate limits, consider using **Wikimedia Enterprise**.

- **Anonymous requests (Without API Key)**: Limited to **500 requests per hour** per IP address.
- **Personal requests**: Authenticated using a personal API token, limited to **5,000 requests per hour**.
- **App-authenticated requests**: Authenticated via OAuth 2.0 client credentials flow, limited to **5,000 requests per hour**.
- **User-authenticated requests**: Authenticated using the OAuth 2.0 authorization code flow, limited to **5,000 requests per hour** per user.

For specific rate limits, refer to the [API catalog](https://api.wikimedia.org/wiki/Rate_limits) for each API.

### Authentication for Wikimedia API
Apps using the Wikimedia API should authenticate their requests using **OAuth 2.0**, providing a secure process for accessing Wikimedia resources. For evaluation and prototyping, a **personal API token** can also be used.

#### App Authentication: OAuth 2.0 Client Credentials Flow
To authenticate on behalf of an app, use the **OAuth 2.0 client credentials flow**. This flow allows apps to access public Wikimedia content by creating credentials, obtaining an access token, and using it to authenticate requests. You can refresh the access token as needed when it expires.

#### User Authentication: OAuth 2.0 Authorization Code Flow
For apps that interact with users (like mobile or desktop apps), use the **OAuth 2.0 authorization code flow**. This method enables users to log in with their Wikimedia account and approve your app’s access. The process involves creating credentials, requesting user authorization, obtaining an access token, and using the token to authenticate requests. Tokens can be refreshed as needed.

#### Personal API Tokens
For testing and prototyping, you can use a **personal API token** tied to your Wikimedia account. These tokens provide an easy way to authenticate requests during development and do not expire.

For more information, refer to the [Authentication Page](https://api.wikimedia.org/wiki/Authentication).

## Features
- Build using Jetpack compose
- 🗞️ News article retrieval from WikiNews API.
- 🖼️ Display of news articles with images and summaries.
- 🧑‍💻 Clean and modern UI built with Jetpack Compose.
- 🏗️ Dependency injection with Hilt.
- 🖼️ Image loading and display with Coil Compose.
- 📱 Offline support and caching for improved performance.
- Dynamic navigation layout build with Navigation-Suite-Scaffold

![](/WikiNews_Screenshots.png)

![](/WikiNewsAppDemo.gif)

## Why WikiNews?
- 🏅 **Fact-based journalism**: WikiNews focuses on verifiable information, reducing the spread of misinformation.
- 🤝 **Community-driven**: WikiNews relies on collaborative efforts, ensuring diverse perspectives and accurate reporting.
- 🌟 **Free access**: The WikiNews API provides free access to news content, democratizing information and promoting knowledge sharing.
