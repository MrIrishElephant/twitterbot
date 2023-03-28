import java.io.IOException;

import ibcs.ia.FootballScoresBotUI;
import ibcs.ia.TwitterService;

public class UnitTest {

  // @org.junit.Test
  // public void testConvertBetsAPIModel() throws Exception {
  //   BetsAPIEntity readObject = BetsAPIConverter.readJSON();
  // }

  @org.junit.Test
  public void testTwitter() {
    var twitterService = new TwitterService();
    var tweets = twitterService.GetTweetHistory();
  }

  @org.junit.Test
  public void testStringBuilder() throws IOException {
    var twitterService = new FootballScoresBotUI();
  }

  @org.junit.Test
  public void testSaveDbFixtures() {
    // // Arrange
    // var saveDB = new SaveDB();
    // try {
    //   saveDB.SaveToAzureDB();
    // } catch (Exception e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
  }
}
