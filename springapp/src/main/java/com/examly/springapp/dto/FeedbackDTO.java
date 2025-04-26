package com.examly.springapp.dto;

import org.hibernate.validator.constraints.Range;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * FeedbackDTO is a data transfer object that encapsulates feedback information.
 * It includes an identifier, a message (with a maximum of 100 characters), 
 * a rating between 1 and 5, and the ID of the user who provided the feedback.
 */
@Data
public class FeedbackDTO {

    // Unique identifier for the feedback.
    private Long feedbackId;

    // The feedback message provided by the user.
    // This cannot be blank and must not exceed 100 characters.
    @NotBlank(message = "Message cannot be blank.")
    @Size(max = 100, message = "Message must not exceed 100 characters.")
    private String message;

    // The rating given by the user.
    // It must be non-null and between 1 and 5.
    @NotNull(message = "Rating cannot be null.")
    @Range(min = 1, max = 5, message = "Rating can be between 1 and 5")
    private Integer rating;

    // The identifier of the user who submitted the feedback.
    // This field cannot be null.
    @NotNull(message = "UserId cannot be null.")
    private Long userId;
}
