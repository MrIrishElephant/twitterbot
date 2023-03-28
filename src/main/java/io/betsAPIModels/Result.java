
package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;
import java.util.Map;

public class Result {
    private String id;
    private String sportID;
    private String time;
    private String timeStatus;
    private Entity league;
    private Entity home;
    private Entity away;
    private String ss;
    private Map<String, Score> scores;
    private Map<String, String[]> stats;
    private String bet365ID;
    private Timer timer;
    private Entity oAway;
    private Entity oHome;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("sport_id")
    public String getSportID() { return sportID; }
    @JsonProperty("sport_id")
    public void setSportID(String value) { this.sportID = value; }

    @JsonProperty("time")
    public String getTime() { return time; }
    @JsonProperty("time")
    public void setTime(String value) { this.time = value; }

    @JsonProperty("time_status")
    public String getTimeStatus() { return timeStatus; }
    @JsonProperty("time_status")
    public void setTimeStatus(String value) { this.timeStatus = value; }

    @JsonProperty("league")
    public Entity getLeague() { return league; }
    @JsonProperty("league")
    public void setLeague(Entity value) { this.league = value; }

    @JsonProperty("home")
    public Entity getHome() { return home; }
    @JsonProperty("home")
    public void setHome(Entity value) { this.home = value; }

    @JsonProperty("away")
    public Entity getAway() { return away; }
    @JsonProperty("away")
    public void setAway(Entity value) { this.away = value; }

    @JsonProperty("ss")
    public String getSs() { return ss; }
    @JsonProperty("ss")
    public void setSs(String value) { this.ss = value; }

    @JsonProperty("scores")
    public Map<String, Score> getScores() { return scores; }
    @JsonProperty("scores")
    public void setScores(Map<String, Score> value) { this.scores = value; }

    @JsonProperty("stats")
    public Map<String, String[]> getStats() { return stats; }
    @JsonProperty("stats")
    public void setStats(Map<String, String[]> value) { this.stats = value; }

    @JsonProperty("bet365_id")
    public String getBet365ID() { return bet365ID; }
    @JsonProperty("bet365_id")
    public void setBet365ID(String value) { this.bet365ID = value; }

    @JsonProperty("timer")
    public Timer getTimer() { return timer; }
    @JsonProperty("timer")
    public void setTimer(Timer value) { this.timer = value; }

    @JsonProperty("o_away")
    public Entity getOAway() { return oAway; }
    @JsonProperty("o_away")
    public void setOAway(Entity value) { this.oAway = value; }

    @JsonProperty("o_home")
    public Entity getOHome() { return oHome; }
    @JsonProperty("o_home")
    public void setOHome(Entity value) { this.oHome = value; }
}
