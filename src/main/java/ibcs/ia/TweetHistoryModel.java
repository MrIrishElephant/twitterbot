package ibcs.ia;

import java.time.LocalDateTime;

public class TweetHistoryModel {
    private long tweetHistoryId;
    private String tweetText;
    private String tweetId;
    private LocalDateTime dateCreated;
    private boolean isDeleted;

    public TweetHistoryModel(long tweetHistoryId, String tweetText, String tweetId, LocalDateTime dateCreated, boolean isDeleted) {
        this.tweetHistoryId = tweetHistoryId;
        this.tweetText = tweetText;
        this.tweetId = tweetId;
        this.dateCreated = dateCreated;
        this.isDeleted = isDeleted;
    }

    public long getTweetHistoryId() {
        return tweetHistoryId;
    }

    public void setTweetHistoryId(long tweetHistoryId) {
        this.tweetHistoryId = tweetHistoryId;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

