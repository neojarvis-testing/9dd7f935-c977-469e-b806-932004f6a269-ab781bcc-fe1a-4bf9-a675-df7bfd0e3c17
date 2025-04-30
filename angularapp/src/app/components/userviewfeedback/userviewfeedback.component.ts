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
  userId:number
  errorMessage: any;
  constructor(private feedbackService: FeedbackService) {}

  ngOnInit() {
    this.userId=+ localStorage.getItem('userId')
    // console.log('User ID:', this.userId);
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedbackService.getFeedbackByUserId(this.userId).subscribe(data => {
      this.feedbacks = data;
    });
  }

  deleteFeedback(feedbackId: number) {
    console.log(feedbackId)
    if (confirm('Are you sure you want to delete?')) {
      this.feedbackService.deleteFeedback(feedbackId).subscribe((data)=>{
        alert("Feedback Deleted")
        this.loadFeedbacks()
      },(error)=>{
        console.log("Error: "+ JSON.stringify(error))
      })
    }
  }

}
