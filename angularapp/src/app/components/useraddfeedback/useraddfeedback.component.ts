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
  feedbackForm!: FormGroup;
  successMessage = '';
  errorMessage = '';
  stars: number[] = [1, 2, 3, 4, 5];
  userid!: number;
  private readonly INCREMENT = 1;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly feedbackService: FeedbackService,
    private readonly router: Router
  ) {}
 
  ngOnInit(): void {
    this.initializeForm();
    this.retrieveUserId();
  }
 
  private initializeForm(): void {
    this.feedbackForm = this.formBuilder.group({
      userId: [null, Validators.required],
      message: ['', [Validators.required, Validators.minLength(10)]],
      rating: [0, [Validators.required, Validators.min(1), Validators.max(5)]]
    });
  }
 
  private retrieveUserId(): void {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userid = +userId;
    } else {
      console.warn('User ID not found in local storage');
    }
  }
 
  addFeedback(): void {
    if (!this.feedbackForm.valid) {
      this.errorMessage = 'Please fill all required fields correctly.';
      return;
    }
 
    this.feedbackForm.patchValue({ userId: this.userid });
 
    this.feedbackService.createFeedback(this.feedbackForm.value).subscribe({
      next: () => {
        this.successMessage = 'Feedback added successfully.';
        setTimeout(() => this.finishSuccess(), 2000); // Auto-close success message
      },
      error: (error) => {
        this.errorMessage = 'Error submitting feedback. Please try again.';
        console.error('Feedback submission error:', error);
      }
    });
  }
 
  setRating(rating: number): void {
    if (rating >= 1 && rating <= 5) {
      this.feedbackForm.patchValue({ rating });
    }
  }
 
  finishSuccess(): void {
    this.successMessage = '';
    this.router.navigateByUrl('/my-feedbacks');
  }
 
  closeErrorModal(): void {
    this.errorMessage = '';
  }
 
  handleKeyDown(event: KeyboardEvent, index: number): void {
    if (this.isEnterOrSpace(event)) {
      event.preventDefault();
      this.setRating(index + this.INCREMENT);
    }
  }

  onStarClick(index: number): void {
    this.setRating(index + this.INCREMENT);
  }

  onStarKeyDown(event: KeyboardEvent, index: number): void {
    if (this.isEnterOrSpace(event)) {
      event.preventDefault();
      this.setRating(index + this.INCREMENT);
    }
  }

  private isEnterOrSpace(event: KeyboardEvent): boolean {
    return event.key === 'Enter' || event.key === ' ';
  }

 
  isStarActive(index: number): boolean {
    return index < (this.feedbackForm.get('rating')?.value ?? 0);
  }
}