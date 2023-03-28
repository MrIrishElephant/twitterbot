package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import ibcs.ia.TwitterService;
import io.betsAPIModels.Event;
import io.betsAPIModels.Odd;
import ibcs.ia.BetsAPIResponse;
import ibcs.ia.BetsAPIService;
import ibcs.ia.TweetGen;

import java.io.IOException;

public class TweetAutoGenerator {
    private final BetsAPIService betsApiService;
    private final TwitterService twitterService;

    //Constructor including specific key for BetsAPI
    //ToDo put this apikey in configuration later
    public TweetAutoGenerator() {
        String apiKey = "hidden for public git";
        this.betsApiService = new BetsAPIService(apiKey);
        this.twitterService = new TwitterService();
    }

    /**
     * @param timerInfo
     * @param context
     */
    @FunctionName("TweetAutoGenerator")
    public void run(
        @TimerTrigger(name = "tweetautogenerator", schedule = "0 * * * * *") String timerInfo,
        ExecutionContext context
    ) {
        context.getLogger().info("TweetAutoGenerator trigger function executed at: " + java.time.LocalDateTime.now());

        try {
            BetsAPIResponse apiResponse = betsApiService.getEvents("1");
            for (Event event : apiResponse.getResults()) {
                // Process the events and generate tweets
                 if (eventHasGoal(event)) {
                    boolean isHomeTeam = isGoalByHomeTeam(event);
                    String tweetText = TweetGen.goalTweet(event, isHomeTeam);
                    twitterService.CreateTweet(tweetText);
                }

                // Example: tweeting about corners
                if (eventHasCorner(event)) {
                    boolean isHomeTeam = isCornerByHomeTeam(event);
                    String tweetText = TweetGen.cornerTweet(event, isHomeTeam);
                    twitterService.CreateTweet(tweetText);
                }

                // Example: tweeting about red cards
                if (eventHasRedCard(event)) {
                    boolean isHomeTeam = isRedCardForHomeTeam(event);
                    String tweetText = TweetGen.redCardTweet(event, isHomeTeam);
                    twitterService.CreateTweet(tweetText);
                }
                // Add more scenarios free kicks, team news etc - make this configurable later
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean eventHasGoal(Event event) {
        int homeScore = event.getHomeGoals();
        int awayScore = event.getAwayGoals();
        return homeScore > 0 || awayScore > 0;
    }
    
    private boolean eventHasCorner(Event event) {
        int homeCorners = event.getHomeCorners();
        int awayCorners = event.getAwayCorners();
        return homeCorners > 0 || awayCorners > 0;
    }
    
    private boolean eventHasRedCard(Event event) {
        int homeRedCards = event.getHomeCards();
        int awayRedCards = event.getAwayCards();
        return homeRedCards > 0 || awayRedCards > 0;
    }
    
    private boolean isGoalByHomeTeam(Event event) {
        int homeScore = event.getHomeGoals();
        return homeScore > 0;
    }
    
    private boolean isCornerByHomeTeam(Event event) {
        int homeCorners = event.getHomeCorners();
        return homeCorners > 0;
    }
    
    private boolean isRedCardForHomeTeam(Event event) {
        int homeRedCards = event.getHomeCards();
        return homeRedCards > 0;
    }


}
