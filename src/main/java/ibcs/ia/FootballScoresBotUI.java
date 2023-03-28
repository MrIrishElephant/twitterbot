package ibcs.ia;

import io.github.redouane59.twitter.dto.tweet.TweetV2.TweetData;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FootballScoresBotUI {
    private boolean premierLeagueSelected;
    private boolean laLigaSelected;
    private boolean serieASelected;
    private String serviceStatus;
    private String tweetId;
    private boolean settingsSaved;
    private AzureDbConnection dbConnection;

    public FootballScoresBotUI() {
        premierLeagueSelected = true;
        laLigaSelected = true;
        serieASelected = true;
        settingsSaved = false;
        serviceStatus = "enabled";
        tweetId = "";

        try {
            this.dbConnection = new AzureDbConnection();
            if(dbConnection.isServiceEnabled())
            { serviceStatus = "enabled";
            }
            else{
                serviceStatus = "disabled";
            }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


    }

    public void setPremierLeagueSelected(boolean selected) {
        premierLeagueSelected = selected;
    }

    public void setLaLigaSelected(boolean selected) {
        laLigaSelected = selected;
    }

    public void setSerieASelected(boolean selected) {
        serieASelected = selected;
    }

    public void setSettingsSaved(boolean saved) {
        settingsSaved = saved;
    }

    public void setServiceStatus(String status) {
        serviceStatus = status;
    }

    public void setTweetId(String id) {
        tweetId = id;
    }

    public String generateHTML() {

        TwitterService tService = new TwitterService();
        var tweets = tService.GetTweetHistory();

        StringBuilder tweetHistoryHTML = new StringBuilder();
        tweetHistoryHTML.append("<table id=\"tweet-history\"><thead><tr><th>Tweet Text</th><th>Tweet ID</th><th>Created At</th></tr></thead><tbody>");
        for (TweetData tweet : tweets) {
            tweetHistoryHTML.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                    tweet.getText(),
                    tweet.getId(),
                    tweet.getCreatedAt().toString()));
        }
        tweetHistoryHTML.append("</tbody></table>");

        return String.format(
            "<!formValuesTYPE html><html><head><title>Football Scores Bot Configuration</title>" + 
            "<style>*{box-sizing:border-box}body{font-family:Arial,sans-serif;margin:0}header{background-color:#1abc9c;color:white;padding:20px;text-align:center}h1{margin:0}main{padding:20px}form{margin:20px 0}h2{margin-top:40px}label{display:block}ul#tweet-history{list-style:none;padding-left:0}ul#tweet-history li{display:flex;align-items:center;padding:10px;border-bottom:1px solid #eee}ul#tweet-history li.header{font-weight:bold;color:#444;background-color:#f0f0f0;border-bottom:2px solid #ccc}ul#tweet-history li span{margin-right:10px}.tweet-type{font-weight:bold;flex-grow:2}.tweet-time{margin-left:10px;color:gray;flex-grow:1}button[type=submit]{margin-top:10px;padding:5px 10px;background-color:#1abc9c;color:white;border:none;border-radius:3px;cursor:pointer}button[type=submit]:hover{background-color:#16a085}footer{background-color:#1abc9c;color:white;padding:10px;text-align:center}</style>" +
            "</head><body><header><h1>Football Scores Bot Configuration</h1></header>" +
            "<main><form method=\"post\"><h2>Choose Competitions/Leagues/Games to Generate Tweets:</h2><input type=\"checkbox\" id=\"premier-league\" name=\"premier-league\" value=\"premier-league\"%s><label for=\"premier-league\">Premier League</label><br><input type=\"checkbox\" id=\"la-liga\" name=\"la-liga\" value=\"la-liga\"%s><label for=\"la-liga\">La Liga</label><br><input type=\"checkbox\" id=\"serie-a\" name=\"serie-a\" value=\"serie-a\"%s><label for=\"serie-a\">Serie A</label><br><button type=\"submit\">Save Competitions to Generate Tweets</button></form>" +
            "<form method=\"post\"><h2>Enable/Disable Service:</h2><label for=\"service-status\">Service Status:</label><select id=\"service-status\" name=\"service-status\"><option value=\"enabled\"%s>Enabled</option><option value=\"disabled\"%s>Disabled</option></select><button type=\"submit\">Save Enable/Disable Service</button></form>" + 
            "<h2>History of Auto-Generated Tweets:</h2><ul id=\"tweet-history\">" + tweetHistoryHTML.toString() + "</ul>" +
            "<form method=\"post\"><h2>Delete/Retract Tweets:</h2><label for=\"tweet-id\">Tweet ID:</label><input type=\"text\" id=\"tweet-id\" name=\"tweet-id\" value=\"%s\"><br><button type=\"submit\">Delete/Retract Tweet</button></form>" + 
            "<form method=\"post\"><h2>Create Manual/Test Tweet:</h2><label for=\"tweet-text\">Tweet Text:</label><input type=\"text\" id=\"tweet-text\" name=\"tweet-text\" value=\"\"><br><button type=\"submit\">Create Tweet</button></form>" + 
            "</main><footer><p>© 2023 Football Scores Bot</p></footer></body></html>",
            
            premierLeagueSelected ? " checked" : "",
            laLigaSelected ? " checked" : "",
            serieASelected ? " checked" : "",
            "enabled".equals(serviceStatus) ? " selected" : "",
            "disabled".equals(serviceStatus) ? " selected" : "",
            tweetId

        );
    }

    public void handleFormSubmission(String formData) {
        // Parse and process form data
        try {

                String[] keyValuePairs = formData.split("&");

                // Store the key-value pairs in a map
                Map<String, String> formValues = new HashMap<>();
                for (String keyValuePair : keyValuePairs) {
                    String[] parts = keyValuePair.split("=");
                    if (parts.length == 2) {
                        String key = parts[0];
                        String value = parts[1];
                        formValues.put(key, value);
                    }
                }

                System.out.println(formValues);
                // Access the values in the map using the keys that correspond to the names of the form fields
                String premierLeagueStatusSelect = formValues.get("premier-league");
                if(premierLeagueStatusSelect != null && !premierLeagueStatusSelect.isEmpty())
                {
                    if(premierLeagueStatusSelect.equals("checked")) {
                        this.premierLeagueSelected =  true;
                        dbConnection.setIsTweetingEnabledByLeagueName("Premier League", true);
                    } else {
                        this.premierLeagueSelected =  false;
                        dbConnection.setIsTweetingEnabledByLeagueName("Premier League", false);
                    }
                    
                }

                String laLigaLeagueStatusSelect = formValues.get("la-liga");
                if(laLigaLeagueStatusSelect != null && !laLigaLeagueStatusSelect.isEmpty())
                {
                    if(laLigaLeagueStatusSelect.equals("checked")) {
                        this.laLigaSelected =  true;
                        dbConnection.setIsTweetingEnabledByLeagueName("La Liga", true);
                    } else {
                        this.laLigaSelected =  false;
                        dbConnection.setIsTweetingEnabledByLeagueName("La Liga", false);
                    }
                    
                }
                
                
                String serieACheckbox = formValues.get("serie-a");
                if(serieACheckbox != null && !serieACheckbox.isEmpty())
                {
                    if(serieACheckbox.equals("checked")) {
                        this.serieASelected =  true;
                        dbConnection.setIsTweetingEnabledByLeagueName("Serie A", true);
                    } else {
                        this.serieASelected =  false;
                        dbConnection.setIsTweetingEnabledByLeagueName("Serie A", false);
                    }
                    
                }

                // Parse service status
                String serviceStatusSelect = formValues.get("service-status");
                if(serviceStatusSelect != null && !serviceStatusSelect.isEmpty())
                {
                    // implement setting is service enabled true or false based on 'Enabled' or 'Disabled' coming from 'service-status' select control
                    if(serviceStatusSelect.equals("enabled")) {
                        dbConnection.setServiceEnabled(true);
                    } else if(serviceStatusSelect.equals("disabled")) {
                        dbConnection.setServiceEnabled(false);
                    }
                }
                
                String tweetIdInput = formValues.get("tweet-id");
                System.out.println("delete tweet: " + tweetIdInput);
                if(tweetIdInput != null && !tweetIdInput.isEmpty())
                {
                    String urlSafe = URLDecoder.decode(tweetIdInput, StandardCharsets.UTF_8);
                    TwitterService tService = new TwitterService();
                    tService.DeleteTweet(urlSafe);
                }

                String createTestTweet = formValues.get("tweet-text");
                System.out.println("tweet text: " + createTestTweet);
                if(createTestTweet != null && !createTestTweet.isEmpty())
                {
                    String urlSafe = URLDecoder.decode(createTestTweet, StandardCharsets.UTF_8);
                    TwitterService tService = new TwitterService();
                    tService.CreateTweet(urlSafe);
                }

          

        } catch (Exception e) {
            System.err.println("Error processing form submission:");
            e.printStackTrace();
        }
    
    }
}

