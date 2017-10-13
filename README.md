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