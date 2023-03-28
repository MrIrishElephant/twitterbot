package ibcs.ia;

import io.betsAPIModels.Event;

public class TweetGen {
    public static String goalTweet(Event event, boolean isHomeTeam) {
        String team = isHomeTeam ? event.getHomeName() : event.getAwayName();
        return "⚽️ Goal! " + team + " scored a goal!";
    }

    public static String cornerTweet(Event event, boolean isHomeTeam) {
        String team = isHomeTeam ? event.getHomeName() : event.getAwayName();
        return "🚩 Corner! " + team + " got a corner!";
    }

    public static String redCardTweet(Event event, boolean isHomeTeam) {
        String team = isHomeTeam ? event.getHomeName() : event.getAwayName();
        return "🟥 Red Card! " + team + " received a red card!";
    }

    public static String yellowCardTweet(Event event, boolean isHomeTeam) {
        String team = isHomeTeam ? event.getHomeName() : event.getAwayName();
        return "🟨 Yellow Card! " + team + " received a yellow card!";
    }
}
