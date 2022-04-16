In the developed world, businesses can protect their brands by trademark registration. During
such a process, the user needs to check whether their trademark is not already taken by
someone else—this is roughly similar to how domain registration works. Your job is to write a
simple backend-only validation service operating on top of a given trademark database.

The requirements are:
1. Download the trademark data from the ftp://ftp.euipo.europa.eu/ (username: opendata
   password: kagar1n)
2. Create a script for setting up the database and populating it just with the state of
   trademarks in 2020 (to keep things simple).
3. Setup the server and implement REST or GraphQL API suitable for trademark-searching
   service. The service is meant to check if a trademark already exists and if it does, return
   the basic properties of it (we leave up to you which ones). For the sake of the
   assignment, let's focus just on word trademarks (not visual/audible/etc), you can
   distinguish them by the "MarkFeature" attribute being "Word". Here's an example of a
   record in the data dump: https://pastebin.com/8G7ysw2X
4. The server should support at least a) searching in a database for exact trademark b)
   fuzzily finding some 'nearest' trademarks (for some meaningful definition of 'nearest')

Folder structure:
  -> docker 
  -> script-generator
  -> source-code

--docker--
  -> docker-compose.yml -> used to start 2 containers, one for DB and one for the JAR, with necessary data and 
structure for application to be used
      -> From a CMD/TERMINAL go to 'docker' folder and hit docker-compose up -d
      -> It will start a postgres DB which will be initialised with the data from script.sql
      -> APP container will start after DB container is ready. Check logs of APP and see when spring boot is starting
  -> script.sql -> script with data to be updated in DB, composed by 'script-generator' code

--script-generator-- 
  -> JAVA code for generating script.sql from 'docker' folder. 
  -> 2 settings: 
        -> path.to.folder.data -> this has to be filled with the location of TradeMarks data from FTP, 
like '2020' folder
        -> path.to.script.sql -> this will be the location for 'script.sql' from 'docker' folder
  -> Reads all XML files from ZIPs from "path.to.folder.data" and writes a SQL structure to 'path.to.script.sql'

--source-code --
  -> JAVA code for trademark searching
  -> 2 rest calls: 
       -> GET -> /?name=text (replace 'text' with desired name) -> returns the exact match for a given 'name' 
or 404 if not found 
       -> GET -> /nearest?name=text -> return a list with 'nearest' matches for given 'name' or 
404 if no match found

For starting the app with all the necessary data please check --docker-- part
