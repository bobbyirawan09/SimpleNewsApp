# SimpleNewsApp
It's a simple app to show news data. I followed the recommendation to implement clean architecture in the app. It still not implement modularization (will implement it soon)

## Tech Stack
Tech I used to create the app 
* [Kotlin](https://kotlinlang.org) - Instead of Java, I used Kotlin to build the app.
* MVVM design pattern - Design pattern I used in presentation layer.
* [Retrofit](https://square.github.io/retrofit/) - Library to fetch data from API.
* [Coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Mainly used for threading, basically you can't say threading, it only for explanation purpose.
* [Coil](https://github.com/coil-kt/coil) - Image loader.
* [Koin](https://insert-koin.io) - Dependency injection. Way more simple than using Dagger 2. Easily understand.
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - For change between fragment. I will try my best to make it single activity.
* [Data binding](https://developer.android.com/topic/libraries/data-binding) - Binding component layout in XML to activity/fragment.
* [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - To fetch data stream from API. In the app, it actually not needed to use it, since the data type would be one-shot, but I just want to try it in this project.

## Design
Design inspired by some awesome peoples, see their dribbble site for more awesome design
* Dashboard design - [Blog App Concept by Tanvir Ahassan Anik](https://dribbble.com/shots/10846460-Blog-App-Concept?utm_source=Clipboard_Shot&utm_campaign=anik117&utm_content=Blog%20App%20Concept&utm_medium=Social_Share)
* News detail design - [TrendNews – International News App](https://dribbble.com/shots/8719178-TrendNews-International-News-App)
* Categories design - [Podcast App | Categories | Mobile](https://dribbble.com/shots/10322517-Podcast-App-Categories-Mobile)
* (Will be adding more later...)

## API Source
I'm using API from [NewsAPI](https://newsapi.org) to get news data. You can use it for free or paid.
