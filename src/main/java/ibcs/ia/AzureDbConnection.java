package ibcs.ia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AzureDbConnection {
    private static final String URL = "jdbc:sqlserver://***";
    private static final String USERNAME = "***";
    private static final String PASSWORD = "***";
    private Connection connection;

    public AzureDbConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void saveTweetHistory(TweetHistoryModel tweetHistory) {
        try {
            String sql = "INSERT INTO TweetHistory (TweetText, TweetId, DateCreated, IsDeleted) VALUES (?, ?, ?, ?)";
    
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, tweetHistory.getTweetText());
                stmt.setString(2, tweetHistory.getTweetId());
                stmt.setTimestamp(3, Timestamp.valueOf(tweetHistory.getDateCreated()));
                stmt.setBoolean(4, tweetHistory.isDeleted());
                stmt.executeUpdate();
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateTweetHistoryIsDeleted(String tweetId) {
        try {
            String sql = "UPDATE TweetHistory SET IsDeleted = ? WHERE TweetId = ?";
    
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setBoolean(1, true);
                stmt.setString(2, tweetId);
                stmt.executeUpdate();
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setServiceEnabled(boolean isEnabled) {
        try {
            String sql = "UPDATE ServiceEnabled SET IsServiceEnabled = ?";
    
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setBoolean(1, isEnabled);
                stmt.executeUpdate();
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isServiceEnabled() {
        boolean isServiceEnabled = false;
    
        try {
            String sql = "SELECT TOP 1 IsServiceEnabled FROM ServiceEnabled";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
    
            if (resultSet.next()) {
                int serviceEnabled = resultSet.getInt("IsServiceEnabled");
                if (serviceEnabled == 1) {
                    isServiceEnabled = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return isServiceEnabled;
    }
    

    public List<FixtureModel> getAllFixtures() {
        List<FixtureModel> fixtures = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Fixture";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int fixtureId = resultSet.getInt("FixtureId");
                String fixtureName = resultSet.getString("FixtureName");
                String homeTeam = resultSet.getString("HomeTeam");
                String awayTeam = resultSet.getString("AwayTeam");
                Date fixtureDate = resultSet.getDate("FixtureDate");
                int leagueId = resultSet.getInt("LeagueId");
                String externalFeedId = resultSet.getString("ExternalFeedId");

                FixtureModel fixture = new FixtureModel(fixtureId, fixtureName, homeTeam, awayTeam, fixtureDate, leagueId, externalFeedId);
                fixtures.add(fixture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fixtures;
    }

    public List<FixtureModel> getFixturesByLeagueId(int leagueId) {
        List<FixtureModel> fixtures = new ArrayList<>();
    
        try {
            String sql = "SELECT * FROM Fixture WHERE LeagueId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, leagueId);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                int fixtureId = resultSet.getInt("FixtureId");
                String fixtureName = resultSet.getString("FixtureName");
                String homeTeam = resultSet.getString("HomeTeam");
                String awayTeam = resultSet.getString("AwayTeam");
                Date fixtureDate = resultSet.getDate("FixtureDate");
                String externalFeedId = resultSet.getString("ExternalFeedId");
    
                FixtureModel fixture = new FixtureModel(fixtureId, fixtureName, homeTeam, awayTeam, fixtureDate, leagueId, externalFeedId);
                fixtures.add(fixture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return fixtures;
    }

    public List<FixtureModel> getFixturesByExternalFeedId(String externalFeedId) {
        List<FixtureModel> fixtures = new ArrayList<>();
    
        try {
            String sql = "SELECT * FROM Fixture WHERE ExternalFeedId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, externalFeedId);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                int fixtureId = resultSet.getInt("FixtureId");
                String fixtureName = resultSet.getString("FixtureName");
                String homeTeam = resultSet.getString("HomeTeam");
                String awayTeam = resultSet.getString("AwayTeam");
                Date fixtureDate = resultSet.getDate("FixtureDate");
                int leagueId = resultSet.getInt("LeagueId");
    
                FixtureModel fixture = new FixtureModel(fixtureId, fixtureName, homeTeam, awayTeam, fixtureDate, leagueId, externalFeedId);
                fixtures.add(fixture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return fixtures;
    }

    public void save(FixtureModel fixture)
    {
        try {
        
        String sql = "";

            if (fixture.getFixtureId() == 0) {
                sql = "INSERT INTO Fixture (FixtureName, HomeTeam, AwayTeam, FixtureDate, LeagueId, ExternalFeedId) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Fixture SET FixtureName = ?, HomeTeam = ?, AwayTeam = ?, FixtureDate = ?, LeagueId = ?, ExternalFeedId = ? WHERE FixtureId = ?";
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, fixture.getFixtureName());
                stmt.setString(2, fixture.getHomeTeam());
                stmt.setString(3, fixture.getAwayTeam());
                stmt.setDate(4, new java.sql.Date(fixture.getFixtureDate().getTime()));
                stmt.setInt(5, fixture.getLeagueId());
                stmt.setString(6, fixture.getExternalFeedId());
                if (fixture.getFixtureId() != 0) {
                    stmt.setInt(7, fixture.getFixtureId());
                }
                stmt.executeUpdate();
         }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsTweetingEnabledByLeagueName(String leagueName) {
        boolean isTweetingEnabled = false;
    
        try {
            String sql = "SELECT IsTweetingEnabled FROM League WHERE LeagueName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, leagueName);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                isTweetingEnabled = resultSet.getBoolean("IsTweetingEnabled");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return isTweetingEnabled;
    }
    
    public void setIsTweetingEnabledByLeagueName(String leagueName, boolean isTweetingEnabled) {
        try {
            String sql = "UPDATE League SET IsTweetingEnabled = ? WHERE LeagueName = ?";
    
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setBoolean(1, isTweetingEnabled);
                stmt.setString(2, leagueName);
                stmt.executeUpdate();
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}

