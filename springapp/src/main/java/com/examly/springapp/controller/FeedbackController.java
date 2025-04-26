package com.examly.springapp.controller;
 
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.dto.FeedbackDTO;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;
 
import jakarta.validation.Valid;
 
//REST controller to handle HTTP requests
@RestController
// Specifies the base URL for the API endpoints in this controller
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackServiceImpl service;
    // Constructor injection of the service (instead of @Autowired)
    public FeedbackController(FeedbackServiceImpl service) {
        this.service = service;
    }
    // Endpoint to create a new feedback
    @PostMapping("/add-feedback")
    public ResponseEntity<FeedbackDTO> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO){
        feedbackDTO=service.addFeedback(feedbackDTO);
        return ResponseEntity.status(201).body(feedbackDTO);
    }
 
    // Endpoint to get a list of all feedback
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        List<Feedback> list=service.getAllFeedback();
        return ResponseEntity.status(200).body(list);
    }
    // Endpoint to get feedback by a specific user's ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUserId(@PathVariable Long userId){
        List<Feedback> list=service.getFeedbackByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }
    // Endpoint to get feedback by its unique ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId){
        Feedback feedback=service.getFeedbackById(feedbackId);
        return ResponseEntity.status(200).body(feedback);
    }
    // Endpoint to delete feedback by its ID
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId){
    boolean result=service.deleteFeedback(feedbackId);
    if(result)
        return ResponseEntity.status(200).body("Feedback Deleted Successfully");
    else
        return ResponseEntity.status(404).body("Not Deleted");
    }

    //Dummy
    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback){
      return ResponseEntity.status(201).body(service.createFeedback(feedback));
    }
}