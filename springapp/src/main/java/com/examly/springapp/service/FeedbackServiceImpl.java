package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.examly.springapp.dto.FeedbackDTO;
import com.examly.springapp.exception.NotFoundException;
import com.examly.springapp.exception.ActionFailedException;
import com.examly.springapp.exception.BadRequestException;
import com.examly.springapp.mapper.FeedbackMapper;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.repository.FeedbackRepo;
// Marks this class as a Service component to define business logic for feedback operations
@Service
public class FeedbackServiceImpl {

    // Logger for tracking the application's flow
    Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    // Dependency injection for Feedback repository
    private final FeedbackRepo feedbackRepo;

    // Constructor for injecting dependencies
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

     // Adds a new feedback to the database

    // public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
    //     logger.info("Method createFeedback started...");
    //     if (feedbackDTO == null || feedbackDTO.getMessage() == null || feedbackDTO.getMessage().isEmpty()) {
    //         throw new BadRequestException("Invalid feedback data. Message cannot be null or empty.");
    //     }
    //     Feedback feedback = FeedbackMapper.mapToFeedbackEntity(feedbackDTO);
    //     try {
    //         Feedback saved = feedbackRepo.save(feedback);
    //         logger.info("Method createFeedback ended...");
    //         return FeedbackMapper.mapToFeedbackDTO(saved);
    //     } catch (Exception e) {
    //         throw new ActionFailedException("Failed to save feedback to the database.");
    //     }
    // }

   // Fetches all feedbacks from the database
    public List<Feedback> getAllFeedback() {
        logger.info("Method getAllFeedback started...");
        List<Feedback> feedbackList = feedbackRepo.findAll();
        if (feedbackList.isEmpty()) {
            throw new NotFoundException("No feedback found.");
        }
        return feedbackList;
    }

   // Fetches all fedbacks by its userId
    public List<Feedback> getFeedbackByUserId(Long userId) {
        logger.info("Method getFeedbackByUserId started...");
        if (userId == null || userId <= 0) {
            throw new BadRequestException("Invalid user ID.");
        }
        List<Feedback> feedbackList = feedbackRepo.findByUserId(userId);
        if (feedbackList.isEmpty()) {
            throw new NotFoundException("No feedback found for the given user ID.");
        }
        return feedbackList;
    }

     // Fetches a feedbacks by its feedbackId
    public Feedback getFeedbackById(Long feedbackId) {
        logger.info("Method getFeedbackById started...");
        if (feedbackId == null || feedbackId <= 0) {
            throw new BadRequestException("Invalid feedback ID.");
        }
        return feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new NotFoundException("Feedback not found for the given ID."));
    }

     // Deletes a feedback by its feedbackId
    public boolean deleteFeedback(Long feedbackId) {
        logger.info("Method deleteFeedback started...");
        if (feedbackId == null || feedbackId <= 0) {
            throw new BadRequestException("Invalid feedback ID.");
        }
        Feedback feedback = feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new NotFoundException("Feedback not found for the given ID."));
        try {
            feedbackRepo.delete(feedback);
            logger.info("Method deleteFeedback ended...");
            return true;
        } catch (Exception e) {
            throw new ActionFailedException("Failed to delete feedback from the database.");
        }
    }
    //Dummy
    public Object createFeedback(Feedback feedback) {
       return feedbackRepo.save(feedback);
    }
}

