package es.dgg.howdoi.stackoverflow;

import java.time.LocalDateTime;

/**
 * Created by david on 1/8/15.
 */
public class StackOverflowQuestion {
    /*{"owner":{
        "reputation":194978,
                "user_id":227665,
                "user_type":"registered",
                "accept_rate":33,
                "profile_image":"https://www.gravatar.com/avatar/ccc83a013527c186c03ea3a475552813?s=128&d=identicon&r=PG",
                "display_name":"codaddict",
                "link":"http://stackoverflow.com/users/227665/codaddict"
    },
        "is_accepted":false,
            "score":209,
            "last_activity_date":1421893849,
            "last_edit_date":1421893849,
            "creation_date":1352188290,
            "answer_id":13246630,
            "question_id":13246597
    },*/

    private boolean isAccepted;
    private int score;
    private LocalDateTime lastActivityDate;
    private LocalDateTime lastEditDate;
    private LocalDateTime creationDate;
    private long answerId;
    private long questionId;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDateTime lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public LocalDateTime getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(LocalDateTime lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
