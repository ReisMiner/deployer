# deployer
Execute a Deployment script on your server when the pipelines are finished to update the service without SSH shenanigans.

Also, please don't look into the Commit History. There's a lot of testing 😳

## How to Use

1. download Latest binary from pipeline
2. make .env file in the same dir as the deployer jar file and fill out these two variables
```
   BOT_TOKEN=discord bot token (make one at https://discord.com/developers/applications)
   GUILD=discord server id
```
3. run the Bot
4. make a webhook on discord
5. make a webhook on github
   1. paste the discord webhook url and add /github at the end
   2. set content type to json
   3. for events select check runs
   4. trigger the webhook once with a pipeline to make sure it works in discord
6. back in discord make right click on the just sent message from the testrun from step 5.4, select app -> "set as embed source"
7. right click again on the message but this time in the apps selection choose "add repo and job to config"
   1. in the new popup enter the repository name (if its not already set)
   2. also enter the job name (if its not already set). You can have multiple jobs in a pipeline and therefore multiple deployer actions.
   3. in the last field enter the command that should be run. ideally this is a small shell script or so.
8. Enjoy
