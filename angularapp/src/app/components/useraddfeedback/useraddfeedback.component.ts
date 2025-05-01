import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  feedbackForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';
  stars: number[] = [1, 2, 3, 4, 5];
  userid: number;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly feedbackService: FeedbackService,
    private readonly router: Router
  ) {}
  handleKeyDown(event: KeyboardEvent, index: number): void {
    // Check for Enter or Space keys
    if (event.key === 'Enter' || event.key === ' ') {
      event.preventDefault(); // Prevent default behavior (such as scrolling)
      this.setRating(index + 1);
    }
  }  
  ngOnInit(): void {
    this.feedbackForm = this.formBuilder.group({
      userId: [null],
      message: ['', [Validators.required, Validators.minLength(10)]],
      rating: [0, [Validators.required, Validators.min(1), Validators.max(5)]]
    });

    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userid = +userId;
    } else {
      console.error('User ID not found in local storage');
    }
  }

  addFeedback(): void {
    if (this.feedbackForm.valid) {
      this.feedbackForm.patchValue({ userId: this.userid });
      this.feedbackService.createFeedback(this.feedbackForm.value).subscribe({
        next: () => {
          // Instead of using alert, we set the success message,
          // which then shows the custom success modal.
          this.successMessage = 'Feedback added successfully.';
        },
        error: (error) => {
          this.errorMessage = 'Error submitting feedback. Please try again.';
          console.error('Error:', error);
        }
      });
    }
  }

  setRating(rating: number): void {
    this.feedbackForm.patchValue({ rating });
  }

  // Closes the success modal and navigates to the feedback list.
  finishSuccess(): void {
    this.successMessage = '';
    this.router.navigateByUrl('/my-feedbacks');
  }

  // Closes the error modal.
  closeErrorModal(): void {
    this.errorMessage = '';
  }
  onStarClick(index: number): void {
    this.setRating(index + 1);
  }
  
  onStarKeyDown(event: KeyboardEvent, index: number): void {
    if (event.key === 'Enter' || event.key === ' ') {
      event.preventDefault(); // Prevent unintended default actions like scrolling
      this.setRating(index + 1);
    }
  }
  
  isStarActive(index: number): boolean {
    return index < (this.feedbackForm.get('rating')?.value || 0);
  }
  
}
