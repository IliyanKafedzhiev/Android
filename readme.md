---
The projects throughout the Android course in SU.

A short description of what each one contains.

##AndoridSU_02.BasicUi

- example of how to use a ScrollView
- shows attaching an event listener to a button
- adding a view dynamically
- displaying a Toast

##AndroidSU_03.AdvancedUI

- switching between activities through options menu 
- shows how to start activities without multiple stacking /intent flag: ``Intent.FLAG_ACTIVITY_REORDER_TO_FRONT``

### LogIn/LoggedIn Activity

- an example for a login page which is skipped (the user is redirected) if already logged
- shows usage of shared preferences
- passing data between intents
- creating/reading a/from text file and parsing it (onto external storage)
- checking external storage state

### Calculator Activity:
 
- an example for relative layout positioning of buttons (note that relative layout is **not** the best option for designing a calculator!)
- not a working calculator
- shows usage of styles (and style inheritance); 
- usage of different resource folders for layouts and styles: proper rendering for different device dimensions and DPIs

### Mail Client Activity:

- an example for using a Chooser for sending a mail

##AndroidSU_04.UserInteraction

- an example for a custom list adapter and interaction with its data through its ListView
- support for older devices using the support library:  ActionBarActivity instead Activity, ``@style/Theme.AppCompat.Light`` for a base theme
- the ViewHolder pattern 
- overriding back button behavior
- retaining list data and position after rotation
- retrieving existing drawables from the resources

##AndroidSU_06.WorkingInBackground

### Shall Crash Activity

- example why we better not modify views directly from a different thread; whether this activity will crash varies by manufacturer (it's a bad thing for sure)

### AsyncTask Demo Activity

- an example of using an AsyncTask for long running operation
- canceling AsyncTask
- the AsyncTask is not retained accross configuration change - for the correct implementation of this a Fragment must be involved

##AndroidSU_07.AccessingTheWeb

- uses the public Stackoverflow API to display the answers of a user by ID in a ListView (title and score)
- using executing a GET request via HTTPClient
- accessing external API
- parsing JSON via the GSON library

##AndroidSU_08.Fragments

- a simple fragments demo
- uses NavigationDrawer from the support library (which is also a Fragment);

##AndroidSU_09.AccessingDB

- an example of a simple SQLite Database interaction
- vanilla android SQLite example
- executing queries over a database
- the DBAdapter pattern is used, providing abstraction over database functionality
