import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-adminaddproduct',
  templateUrl: './adminaddproduct.component.html',
  styleUrls: ['./adminaddproduct.component.css']
})
export class AdminaddproductComponent implements OnInit {
  addProductForm:FormGroup;
  userId:number
  submitted: boolean = false
   // Popup properties
   showPopup: boolean = false
   popupTitle: string = ''
   popupMessage: string = ''

  constructor(private service : ProductService, private fb : FormBuilder, private router : Router) {
    this.addProductForm = this.fb.group({
      name : ['',[Validators.required, Validators.pattern('^[a-zA-Z0-9 ]{3,20}$')]],
      description : ['',[Validators.required, Validators.pattern('[a-zA-Z0-9. ]{3,100}$')]],
      price : ['',[Validators.required, Validators.min(1)]],
      stock : ['',[Validators.required, Validators.min(0)]],
      category : ['',[Validators.required]],
      photoImage : ['',[Validators.required]],
      userId : [0]
    });
   }

  backgroundUrl:string;
  ngOnInit(): void {
    this.userId=+ localStorage.getItem('userId')
  }

  createProduct() {
    this.addProductForm.value.userId=this.userId
    console.log(this.addProductForm.value.userId)
    this.service.createProduct(this.addProductForm.value).subscribe(()=>{
      console.log(this.addProductForm.value)
      this.showPopupMsg("Success", "Product added Successfully!!!");
      this.router.navigate(['/viewproduct']);
    }, (error)=>{
        console.log(JSON.stringify(error))
        this.showPopupMsg("Error", "Product Failed to add!!!");
    })
  }
  
  // Store the selected file (if needed for further processing/upload)
  handleFileChange(event: any) {
    const file = event.target.files[0]; // Get the file from input
    if (file) {
      const formData = new FormData();
      formData.append('photoImage', file); // Attach file directly
      this.addProductForm.patchValue({ photoImage: file.name }); // Optional: store file name if needed
    }
  }

    // Helper method to show the custom popup
    showPopupMsg(title: string, message: string): void {
      this.popupTitle = title;
      this.popupMessage = message;
      this.showPopup = true;
    }
  
    // Call this method when the user closes the popup
    closePopup(): void {
      this.showPopup = false;
      // If login was successful, navigate to the home page
      if (this.popupTitle === "Success") {
        this.router.navigate(['/']);
      }
    }
}
