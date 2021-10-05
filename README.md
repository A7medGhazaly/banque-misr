# banque-misr
paticipant.sql is contain all scripts with dml data
must add group data first and DB in table called "champion_group"

apis---->
first api post("/participant") for adding new participant and link him to a group
get("/participants") for fetching all participants
post("/start-champion") for create match with all participant and return this matches
put(""/match-winner"") for assigning match winner
post("/close-round") to close all matches created before and get ready for next round
put("/league-winner") for assiging league winner and send him email


business role----->
i had created script for 4 groups every group must have the same number of participants as can start match for example i add script for participant too
cannot start new round using this api post("/start-champion") after close the brevious round
cannot close round before adding all match winner for every match

properties file->
must add a valid email and password to send email by it in properties file
DB connection in DataBaseConfiguration.class
i had use api exceptio handler for contolling api exception


