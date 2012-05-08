java-rest-backbone
==================

A simple Java REST app using backbone.JS for CloudBees.

Clone the app locally
    git clone git@github.com:swashbuck1r/java-rest-backbone.git myapp

Verify it runs locally (this uses in-memory storage by default)
    cd myapp
    mvn bees:run

Deploy the app to CloudBees
    mvn bees:deploy -Dbees.appid=MY_APPID

Create a database
    bees db:create -u MY_DB_USER -p MY_DB_PASS MY_DATABASE_ID

Bind the database to the app
    bees app:bind -db DATABASE_ID -a MY_APPID -as words

Reconfigure the app to run in database mode, instead of in-memory.
Note: "mode" is looked up as a ServletContext param by the app
    bees config:set -a MY_APPID mode=db

Restart the app to apply new settings and resource bindings
    bees app:restart -a MY_APPID
