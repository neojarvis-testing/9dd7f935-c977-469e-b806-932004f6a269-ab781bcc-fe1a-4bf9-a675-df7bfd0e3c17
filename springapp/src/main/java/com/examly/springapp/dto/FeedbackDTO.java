package com.examly.springapp.dto;

import org.hibernate.validator.constraints.Range;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;

public class FeedbackDTO {
    private Long feedbackId;

    @NotBlank(message = "Message cannot be blank.") //Ensures message is not null or empty
    @Size(max = 100, message = "Message must not exceed 100 characters.") //Limits message to 100 characters
    private String message;

    @NotNull(message = "Rating cannot be null.") //Ensures rating is not null or empty
    @Range(min = 1, max = 5, message = "Rating can be between 1 and 5") //Ensures rating falls between 1-5
    private Integer rating;

    @NotNull(message = "UserId cannot be null.") //Ensures userId cannot be null or empty
    private Long userId;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
