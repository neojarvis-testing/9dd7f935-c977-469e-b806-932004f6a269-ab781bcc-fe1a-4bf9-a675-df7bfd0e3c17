import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  errorMessage: string = ''; // For handling errors
  feedbacks: Feedback[] = [];

  // Refactored constructor
  constructor(private readonly feedbackService: FeedbackService) {}

  // Refactored ngOnInit method
  ngOnInit(): void {
    this.loadFeedbacks();
  }

  private loadFeedbacks(): void {
    this.feedbackService.getAllFeedbacks().subscribe({
      next: (data) => this.handleFeedbackSuccess(data),
      error: (error) => this.handleFeedbackError(error)
    });
  }

  // Refactored method to handle successful API responses
  private handleFeedbackSuccess(data: Feedback[]): void {
    this.feedbacks = data;
    this.errorMessage = ''; // Clear any previous error message
  }

  // Refactored method to handle errors
  private handleFeedbackError(error: any): void {
    if (this.isFeedbackNotFoundError(error)) {
      this.feedbacks = [];
      this.errorMessage = "No feedback found.";
    } else {
      this.errorMessage = "Error loading feedbacks.";
    }
  }

  // Utility method to check if the error is a "Not Found" error
  private isFeedbackNotFoundError(error: any): boolean {
    return (
      error.status === 404 ||
      (error.error &&
        (error.error.data === "No feedback found." ||
         error.error.message === "Not Found"))
    );
  }
}
