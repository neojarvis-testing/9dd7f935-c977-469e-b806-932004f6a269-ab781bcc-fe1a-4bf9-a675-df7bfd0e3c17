package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.examly.springapp.dto.FeedbackDTO;
import com.examly.springapp.exception.NotFoundException;
import com.examly.springapp.mapper.FeedbackMapper;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.repository.FeedbackRepo;

// Marks this class as a Service component to define business logic for feedback operations
@Service
public class FeedbackServiceImpl {
     // Logger to track method execution in the application logs
    Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    private final FeedbackRepo feedbackRepo;
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo=feedbackRepo;
    }
    // Adds a new feedback to the database
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        logger.info("Method createFeedback started...");
        Feedback feedback = FeedbackMapper.mapToFeedbackEntity(feedbackDTO);
       Feedback saved =  feedbackRepo.save(feedback);
       return FeedbackMapper.mapToFeedbackDTO(saved);
    }
    // Fetches all feedbacks from the database
    public List<Feedback> getAllFeedback() {
        logger.info("Method getAllFeedback started...");
        return feedbackRepo.findAll();
    }
    // Fetches all fedbacks by its userId
    public List<Feedback> getFeedbackByUserId(Long userId) {
        logger.info("Method getFeedbackByUserId started...");
        return feedbackRepo.findByUserId(userId);
    }
    // Fetches a fedbacks by its id
    public Feedback getFeedbackById(Long feedbackId) {
        logger.info("Method getFeedbackById started...");
      return feedbackRepo.findById(feedbackId).orElse(null);
    }
    // Deletes a feedback by its ID
    public boolean deleteFeedback(Long feedbackId) {
        logger.info("Method deleteFeedback started...");
        
        Feedback feedback = feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new NotFoundException("FeedbackId not Found"));
        
        feedbackRepo.delete(feedback);
        
        logger.info("Method deleteFeedback ended...");
        return true;
    }
    
}
