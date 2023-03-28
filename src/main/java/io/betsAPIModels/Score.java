
// Score.java

package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;

public class Score {
    private String home;
    private String away;

    @JsonProperty("home")
    public String getHome() { return home; }
    @JsonProperty("home")
    public void setHome(String value) { this.home = value; }

    @JsonProperty("away")
    public String getAway() { return away; }
    @JsonProperty("away")
    public void setAway(String value) { this.away = value; }
}
