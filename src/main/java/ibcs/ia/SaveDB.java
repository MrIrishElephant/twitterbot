package ibcs.ia;

import java.sql.Date;

import io.betsAPIModels.Result;

public class SaveDB {

    public void SaveToAzureDB() throws Exception
    {
        var azureDb = new AzureDbConnection();
            var betsAPIobject = BetsAPIConverter.readJSON();
            // loop through deserialized Json object, and save to DB if fixture does not already exist (using external id as reference)
            for (Result res : betsAPIobject.getResults()) {

                var doesFixtureExist = azureDb.getFixturesByExternalFeedId(res.getBet365ID());
                if(doesFixtureExist.isEmpty())
                {
                    var newFixture = new FixtureModel(0, res.getHome().getName() + " v " + res.getAway().getName(), res.getHome().getName(), res.getAway().getName(), fromUnixTime(res.getTime()), 1, res.getBet365ID());
                    azureDb.save(newFixture);
                }
            }
    }

    public void SaveTweet(String TweetText, String TweetId) throws Exception
    {
        var azureDb = new AzureDbConnection();
            var betsAPIobject = BetsAPIConverter.readJSON();
            // loop through deserialized Json object, and save to DB if fixture does not already exist (using external id as reference)
            for (Result res : betsAPIobject.getResults()) {

                var doesFixtureExist = azureDb.getFixturesByExternalFeedId(res.getBet365ID());
                if(doesFixtureExist.isEmpty())
                {
                    var newFixture = new FixtureModel(0, res.getHome().getName() + " v " + res.getAway().getName(), res.getHome().getName(), res.getAway().getName(), fromUnixTime(res.getTime()), 1, res.getBet365ID());
                    azureDb.save(newFixture);
                }
            }
    }

    public Date fromUnixTime(String string) {
        long unixTimeInSeconds = Long.parseLong(string);
        return new Date(unixTimeInSeconds * 1000);
    }
}
