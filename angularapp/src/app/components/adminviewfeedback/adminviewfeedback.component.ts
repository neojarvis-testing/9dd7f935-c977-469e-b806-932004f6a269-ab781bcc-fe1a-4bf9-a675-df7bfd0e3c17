import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  errorMessage: string = ''; // For handling errors

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.loadAllFeedbacks(); // Load feedbacks when the component is initialized
  }

  loadAllFeedbacks(): void {
    this.feedbackService.getAllFeedbacks().subscribe({
      next: (data) => {
        this.feedbacks = data; // Assign the data to the feedback array
      },
      error: (err) => {
        console.error('Error loading feedbacks:', err);
        this.errorMessage = 'Failed to load feedbacks. Please try again later.';
      }
    });
  }
}
