# Technical test

### Dorna MOTO GP. A project written in Kotlin

For this test, I've used a MVVM pattern, using new [architecture components] libraries recommended by Google. 

I've decided to use a local cache for network responses. This layer is implemented with the new [Room persistance library]. This choice is made just to use the last components provided by Google. I've experience with Realm too (this cache is in use on my current job)

[Dagger 2] is used to provide the necessary dependencies to the different modules and components. The version used is 2.11, so the last recommended dagger structure to inject dependencies is in use on this project.

[Glide] is used to manage image loading and caching. 

There are just two classes with unit test, I wish I would have more time to write more unit tests, but I didn't want to extend the delivery time.

Those are the most important libraries used. Hope you like the project and the architecture implemented.
Thanks for your time!

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [architecture components]: <https://developer.android.com/topic/libraries/architecture/viewmodel.html>
   [Room persistance library]: <https://developer.android.com/topic/libraries/architecture/room.html>
   [Glide]: <https://github.com/bumptech/glide>
   [Dagger 2]: <https://google.github.io/dagger/android.html>