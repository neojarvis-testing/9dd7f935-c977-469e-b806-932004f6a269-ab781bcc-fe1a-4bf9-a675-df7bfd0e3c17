package com.examly.springapp.model;

// Importing annotations and classes for mapping this class to a database table.

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


// Marking this class as an Entity representing a database table.
@Entity
@Table(name ="feedback")
public class Feedback {

    // Primary key for the table, auto-generated.
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long feedbackId;

    // Mapping feedback to a specific user (Many-to-One relationship).
    @ManyToOne
    // Column name in the database for the foreign key.
    @JoinColumn(name="userId")
    private User user;
    private String message;
    private Integer rating;

    //setters and getters
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Long getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
}
