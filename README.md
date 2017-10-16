# School Project - Android app

This is a repository for an application that is being developed for a school project - an Android news application that works locally using SQLite databases on android systems.
This repository will be updated almost daily or when something is finalized and is fully working, and notes for the update will be written down in the readme file (here) with the date of the update. Each commit will have the date of the update in the comment to help distinguish updates.

## Update 11/OCT/2017:
Created repository, added README.md

## Update 12/OCT/2017:
Created the Database Manager along with the Article class. Need to start working on the layouts, along with adding a new task and showing a task - Use one seperate layout with already programmed scroll layout along with images at the top. Structure: Title, Image, body, comments (if I have time).

## Update 13/OCT/2017:
Began working on the functions for the articles, working on the get articles descending per date in an array, need to finish it. Got the cursor, need to start the new array of the articles and add each article individually per the given fields in the database from the cursor that we queried. Work needs to be done by 14/OCT/2017. Thinking of adding a comment section for each article and create a new table in the database, in case I have extra time.

## Update 13/OCT/2017 19:55:
Finished sorting the results descending per date inside the getArticlesByDate inside the database manager.

## Update 13/OCT/2017 23:15:
Updated the database manager properly, began with the layout and finished rendering all the articles per date and showing them in the main page after retrieving them from the database properly. Added WipeTable and addArticle in case these functions are needed to wipe the table in the database or add a dummy article for testing. The current main page only shows 10 articles at the maximum, and that could be changed inside the MainActivity's renderArticlesPerDate.

## Update 14/OCT/2017 10:15:
Added the article view class, still didnt work on it. Need to change from local SQLite Database to a firebase database, already initated and set up in the android studio. Need to begin working on viewing the article inside the xml file using the article view class.

## Update 15/OCT/2017 15:40:
Changed the application from local SQLite databases to Firebase.

## Update 15/OCT/2017 16:20:
Added the Add Article activity, finalized the render articles from database, along with adding the article into Firebase without any errors. Need to work on article view which shows one specific article, along with researching about the media storage on firebase for the photos.

## Update 16/OCT/2017 10:20:
Added the upload photo to firebase storage buckets, giving the photos the ID of the last article so they can add articles. Added the photo link into the article and it stores in the firebase. Still need to work on viewing the article and the view all articles to add the thumbnail from the firebase.

## Update 16/OCT/2017 12:15:
Fully added the upload photos and now showing the photos in the view all artices page.