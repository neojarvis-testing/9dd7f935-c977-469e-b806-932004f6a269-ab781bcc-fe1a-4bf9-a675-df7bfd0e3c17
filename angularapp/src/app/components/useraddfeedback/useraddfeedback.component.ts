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
    private formBuilder: FormBuilder,
    private feedbackService: FeedbackService,
    private router: Router
  ) {}

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
          alert('Feedback added successfully');
          this.router.navigateByUrl('/my-feedbacks');
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

  resetForm(): void {
    this.feedbackForm.reset({ message: '', rating: 0 }, { emitEvent: false });
  }
}
