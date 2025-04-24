package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dto.FeedbackDTO;
import com.examly.springapp.model.Feedback;

// Interface defining the contract for Feedback service operations
public interface FeedbackService {
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
     public List<Feedback> getAllFeedback();
     public List<Feedback> getFeedbackByUserId(Long userId);
     public Feedback getFeedbackById(Long feedbackId);
     public boolean deleteFeedback(Long feedbackId) ;
}
