Poke Project
=========================

An application which illustrates a simplified MVVM architecture for executing a network call and showing
the list of content inside a `RecyclerView`, also searching through that list and saving the favourites item on local database.
For implementing the MVVM architecture I used JetPack and
recommended best practices


Libraries Used
--------------
* [Architecture] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
    * [Lifecycles] - Create a UI that automatically responds to lifecycle events.
    * [LiveData] - Build data objects that notify views when the underlying database changes.
    * [ViewModel] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
    * [Navigation Components] for navigation
    * [Coroutines] for multithreading
* [UI] - Details on why and how to use UI Components in your apps - together or separate
    * [RecyclerView] - Powerful component for displaying a scrolling list of items based on large data sets.
* Third party
    * [Glide] for image loading
    * [Retrofit] for network requests
    * [Gson] for serialisation/deserialization
    * [ROOM] for local database
  

Areas to grow
-----------------
 The is still room to improve the whole project. For now I just wanted to show the ability to work with
 MVVM architecture and the recommended by Google libraries.In addition, I still need to add unit tests and UI tests, handle a no internet connection case, manage the back stack, etc.
 
