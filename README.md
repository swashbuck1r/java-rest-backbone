backbone.js and RESTful backend (JAX-RS)
==================

Backbone.js (http://backbonejs.org/) is a popular lightweight javascript framework for giving structure to complex javascript client side applications. 
This works nicely with restful frameworks at the back end (the back end is a set of restful services). 
When you deploy this clickstart, it will create a javascript/backbone app, a source repo with continuous deplopyment via Jenkins, and a database. 

Feel free to use this as a starter project. Launch the clickstart, clone the repo and every change you push will be built and deployed.

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/java-rest-backbone/master/clickstart.json"><img src="https://s3.amazonaws.com/cloudbees-downloads/clickstart/clickstart-now.png"/></a>



To deploy this manually: 

Clone the app locally

    git clone git://github.com/CloudBees-community/java-rest-backbone.git myapp

Verify it runs locally (this uses in-memory storage by default)

    cd myapp
    mvn bees:run

Deploy the app to CloudBees (as WAR)
--------------------------------------

    mvn bees:deploy -Dbees.appid=MY_APPID


Deploy the app to CloudBees (as source)
--------------------------------------

    bees app:create --withCD MY_APPID
    [... your new CLOUDBEES GIT URL will be printed ...]
    git remote add cloudbees YOUR_NEW_CLOUDBEES_GIT_URL
    git push cloudbees master


Bind the app to a database
---------------------------
Create a database

    bees db:create -u MY_DB_USER -p MY_DB_PASS MY_DATABASE_ID

Bind the database to the app

    bees app:bind -db DATABASE_ID -a MY_APPID -as words

Reconfigure the app to run in database mode, instead of in-memory.
Note: "mode" is looked up as a ServletContext param by the app

    bees config:set -a MY_APPID mode=db

Restart the app to apply new settings and resource bindings

    bees app:restart -a MY_APPID
