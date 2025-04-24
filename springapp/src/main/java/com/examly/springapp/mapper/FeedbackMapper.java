package com.examly.springapp.mapper;

import com.examly.springapp.dto.FeedbackDTO;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;

public class FeedbackMapper {
    // Maps FeedbackDTO to Feedback
    public static Feedback mapToFeedbackEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDTO.getFeedbackId());
        feedback.setMessage(feedbackDTO.getMessage());
        feedback.setRating(feedbackDTO.getRating());
        // Map userId to User entity
        if (feedbackDTO.getUserId() != null) {
            User user = new User(); // Create a User entity and set its ID
            user.setUserId(feedbackDTO.getUserId());
            feedback.setUser(user);
        }
        return feedback;
    }

    // Maps Feedback to FeedbackDTO
    public static FeedbackDTO mapToFeedbackDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        feedbackDTO.setMessage(feedback.getMessage());
        feedbackDTO.setRating(feedback.getRating());
        // Map User entity to userId
        if (feedback.getUser() != null) {
            feedbackDTO.setUserId(feedback.getUser().getUserId());
        }
        return feedbackDTO;
    }
}
