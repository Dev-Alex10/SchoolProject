# SchoolProject Main Goal

My main is to create a small project to understand some technologies better.

The final goal is to have an app with:

* A list of class modules to choose from a list.
* Inside the module having a "class" so the user can learn and after that a quiz with a timer and
  score.
* Profile in which the user can change some basic info.
* Basic settings tab (Still tbd the content of this)

## Tech Stack

### Some libraries/concepts it uses 🎓 : 

* Kotlin Coroutines & Flow for concurrency and reactivity
* Hilt for dependency injection
* Room for local storage
* Multi-module architecture
* Composite-builds to reduce the code in each build.gradle, and make build time faster
* Retrofit for HTTP calls
* Coil for image loading
* MVI design pattern (if you see anything antipattern, do reach me as I'm still learning the best practices)
[//]: # (* Datastore for isOffline mode)

## How to run the project

### Firebase Setup 🛠️

Place `google.services.json` in `app/`

**Note**: You will need to have the project as `my.schoolProject` or change it in build.gradle.kts(
app) 
