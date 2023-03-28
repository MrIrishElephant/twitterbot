package ibcs.ia;

public class LeagueModel {
    private long leagueId;
    private long countryId;
    private String leagueName;
    private String externalLeagueId;
    private boolean isTweetingAvailable;
    private boolean isTweetingEnabled;
    
    public LeagueModel() {
        
    }
    
    public LeagueModel(long leagueId, long countryId, String leagueName, String externalLeagueId, boolean isTweetingAvailable, boolean isTweetingEnabled) {
        this.leagueId = leagueId;
        this.countryId = countryId;
        this.leagueName = leagueName;
        this.externalLeagueId = externalLeagueId;
        this.isTweetingAvailable = isTweetingAvailable;
        this.isTweetingEnabled = isTweetingEnabled;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getExternalLeagueId() {
        return externalLeagueId;
    }

    public void setExternalLeagueId(String externalLeagueId) {
        this.externalLeagueId = externalLeagueId;
    }

    public boolean isTweetingAvailable() {
        return isTweetingAvailable;
    }

    public void setTweetingAvailable(boolean tweetingAvailable) {
        isTweetingAvailable = tweetingAvailable;
    }

    public boolean isTweetingEnabled() {
        return isTweetingEnabled;
    }

    public void setTweetingEnabled(boolean tweetingEnabled) {
        isTweetingEnabled = tweetingEnabled;
    }
}
