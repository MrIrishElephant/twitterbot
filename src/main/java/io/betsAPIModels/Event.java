package io.betsAPIModels;

import java.util.List;

public class Event {
    private String id;
    private String time;
    private String leagueId;
    private String homeName;
    private String awayName;
    private int homeGoals;
    private int awayGoals;
    private int homeCorners;
    private int awayCorners;  
    private int homeCards;
    private int awayCards;
    private List<Odd> odds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public int getAwayCorners() {
        return awayCorners;
    }

    public void setAwayCorners(int awayCorners) {
        this.awayCorners = awayCorners;
    }

    public int getHomeCorners() {
        return homeCorners;
    }

    public void setHomeCorners(int homeCorners) {
        this.homeCorners = homeCorners;
    }
    
    public int getAwayCards() {
        return awayCards;
    }

    public void setAwayCards(int awayCards) {
        this.awayCards = awayCards;
    }

    public int getHomeCards() {
        return homeCards;
    }

    public void setHomeCards(int homeCards) {
        this.homeCards = homeCards;
    }  
}
