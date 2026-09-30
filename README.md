
# Smart Pantry Manager 
Smart Pantry Manager is an Android Application created using Java for my Mobile App Development Assignment.
This application focuses on helping users keep track of their ingredients that they currently have at home.
It suggests recipes that can be prepared using those ingredients.
The main purpose of my smart pantry manager is to help reduce food waste by encouraging users to utilize ingredients that are already available in their home.

# Features

- Add new pantry items
- view saved pantry items
- edit existing pantry items
- delete pantry items
- store ingredient name/ category/ quantity and unit
- store an optional expiry date
- validate user input
- save pantry data between application sessions
- access a collection of 20 preloaded recipes
- suggest recipes based on ingredients currently available
- strictly match recipes according to required ingredients and quantities
- handle simple ingredient name and unit differences 
- view recipe ingredients and preparation steps
- display and notify feedback when no recipes match the current pantry
- access a settings screen 
- navigate through the applicatiion using a toolbar menu

# Database 
Smart pantry manager uses sqlite with sqliteopenhelper for local data storage.
AQLite was chosen because it provides lightweight and persistent storage directly on the android device.
The application does not require an internet connection/ external database server to store and retrieve pantry information.
SQLite is suitable because it supports CRUD functions required for managing pantry items.
Pantry information will be stored after the application is closed and reopened.

# How to run the application 
1. Download the smart pantry manager repository from Github. 
2. Open android studio.
3. Select "open" and choose the SmartPantryManager project folder.
4. Allow gradle to sync and finish loading the project.
5. Start an android emulator/ connect to a compatible android device.
6. Select the device in Android studio.
7. Click "Run"
8. The smart pantry manager home screen will open.

# Developer

Developed by Eryn Richards for Mobile App Development 700. 



