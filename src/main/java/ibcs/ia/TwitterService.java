package ibcs.ia;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.endpoints.AdditionalParameters;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2.TweetData;
import io.github.redouane59.twitter.signature.TwitterCredentials;

/*
 * This is going to consume the twitter API
 * 
 * This will be used to actually call 
 * 
 * Will have to look at Twitter API, to see what functions would be needed in order to call them
 * 
 * 
 * 
 */
public class TwitterService {

      private TwitterClient twitterClient;
      private AzureDbConnection dbConnection;

      public TwitterService() {
          this.twitterClient = new TwitterClient(TwitterCredentials.builder()
          .accessToken("hidden from public git")
          .accessTokenSecret("hidden from public git")
          .apiKey("hidden from public git")
          .apiSecretKey("hidden from public git")
          .build());

          try {
            this.dbConnection = new AzureDbConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
      }

      public void TestTweet()
      {
        twitterClient.postTweet("Test tweet");  
      }

      public void CreateTweet(String tweetText) {
        var createdTweet = twitterClient.postTweet(tweetText);
        // Save the tweet to the database
        LocalDateTime now = LocalDateTime.now();
        TweetHistoryModel tweetHistory = new TweetHistoryModel(0, tweetText, createdTweet.getId(), now, false);
        dbConnection.saveTweetHistory(tweetHistory);
    }

      public void DeleteTweet(String tweetId)
      {
        twitterClient.deleteTweet(tweetId);
        // Update the tweet in the database to mark it as deleted
        dbConnection.updateTweetHistoryIsDeleted(tweetId);  
      }

      public List<TweetData> GetTweetHistory()
      {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        AdditionalParameters additionalParameters = AdditionalParameters.builder().maxResults(10).startTime(oneMonthAgo).build();
        TweetList tweetList = twitterClient.getUserTimeline("1329489980016779266", additionalParameters);
        List<TweetData> tweets = tweetList.getData();

        return tweets;
        
      }

}
