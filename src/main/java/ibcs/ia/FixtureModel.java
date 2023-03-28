package ibcs.ia;

import java.sql.Date;

/*
 * this maps to a table in the database
 * dependency with Azure, in order to save the fixture
 */
public class FixtureModel {
    private int fixtureId;
    private String fixtureName;
    private String homeTeam;
    private String awayTeam;
    private Date fixtureDate;
    private int leagueId;
    private String externalFeedId;
    
    // Constructor
    public FixtureModel(int fixtureId, String fixtureName, String homeTeam, String awayTeam, Date fixtureDate, int leagueId, String externalFeedId) {
        this.fixtureId = fixtureId;
        this.fixtureName = fixtureName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fixtureDate = fixtureDate;
        this.leagueId = leagueId;
        this.externalFeedId = externalFeedId;
    }
    
    // Getters and setters for each field
    public int getFixtureId() {
        return fixtureId;
    }
    
    public void setFixtureId(int fixtureId) {
        this.fixtureId = fixtureId;
    }
    
    public String getFixtureName() {
        return fixtureName;
    }
    
    public void setFixtureName(String fixtureName) {
        this.fixtureName = fixtureName;
    }
    
    public String getHomeTeam() {
        return homeTeam;
    }
    
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
    
    public String getAwayTeam() {
        return awayTeam;
    }
    
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
    
    public Date getFixtureDate() {
        return fixtureDate;
    }
    
    public void setFixtureDate(Date fixtureDate) {
        this.fixtureDate = fixtureDate;
    }
    
    public int getLeagueId() {
        return leagueId;
    }
    
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getExternalFeedId() {
        return externalFeedId;
    }
    
    public void setExternalFeedId(String externalFeedId) {
        this.externalFeedId = externalFeedId;
    }
}
