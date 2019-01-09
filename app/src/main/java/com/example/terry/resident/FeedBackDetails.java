package com.example.terry.resident;

public class FeedBackDetails {
    String feedBackID;
    String feedBackDetails;
    String feedBackDate;

    public FeedBackDetails() {
    }

    public FeedBackDetails(String feedBackID, String feedBackDetails, String feedBackDate) {
        this.feedBackID = feedBackID;
        this.feedBackDetails = feedBackDetails;
        this.feedBackDate = feedBackDate;
    }

    public String getFeedBackID() {
        return feedBackID;
    }

    public String getFeedBackDetails() {
        return feedBackDetails;
    }

    public String getFeedBackDate() {
        return feedBackDate;
    }
}
