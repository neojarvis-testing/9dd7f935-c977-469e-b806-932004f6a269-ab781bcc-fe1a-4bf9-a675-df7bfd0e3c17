import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  userId: any;
  errorMessage: any;

  // Modal state variables
  showDeleteModal: boolean = false;
  showResultModal: boolean = false;
  selectedFeedbackId: number | null = null;
  resultModalTitle: string = '';
  resultModalMessage: string = '';

  constructor(private readonly feedbackService: FeedbackService) {}

  ngOnInit() {
    this.userId = localStorage.getItem('userId');
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedbackService.getFeedbackByUserId(this.userId).subscribe(
      (data) => {
        this.feedbacks = data;
      },
      (error) => {
        // Check for the 404 error or other specifics in the error response
        if (error.status === 404 || (error.error && error.error.data === "No feedback found")) {
          this.feedbacks = [];
          this.errorMessage = "No feedbacks available.";
        } else {
          this.errorMessage = "Error loading feedbacks.";
        }
      }
    );
  }

  // Open the custom delete confirmation modal
  openDeleteModal(feedbackId: number) {
    this.selectedFeedbackId = feedbackId;
    this.showDeleteModal = true;
  }

  // Close the delete modal
  closeDeleteModal() {
    this.showDeleteModal = false;
    this.selectedFeedbackId = null;
  }

  // On confirming deletion, attempt to delete and show result modal.
  confirmDelete() {
    if (this.selectedFeedbackId !== null) {
      this.feedbackService.deleteFeedback(this.selectedFeedbackId).subscribe(
        (data) => {
          this.resultModalTitle = "Success";
          this.resultModalMessage = "Feedback Deleted Successfully.";
          this.showResultModal = true;
          this.loadFeedbacks();
        },
        (error) => {
          this.resultModalTitle = "Error";
          this.resultModalMessage =
            "There was an error deleting the feedback.";
          this.showResultModal = true;
        }
      );
    }
    this.closeDeleteModal();
  }

  // Close the results modal
  closeResultModal() {
    this.showResultModal = false;
  }

}
