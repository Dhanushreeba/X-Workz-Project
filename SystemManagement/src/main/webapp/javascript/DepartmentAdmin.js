 let fieldsChecks = {
        "adminName": false,
        "departmentName": false,
        "email": false,
        "contactNumber": false,
        "alternateContactNumber": false,
        "agree": false
    };

    function validateAndEnableSubmit() {
        let flag = false;

        for (let value of Object.values(fieldsChecks))
         {
            if (!value)
            {
                flag = true;
                break;
            }
        }

        if (!flag) {
            document.getElementById("submit").removeAttribute("disabled");
        } else {
            document.getElementById("submit").setAttribute("disabled", "");
        }
    }

    // adminName
    function adminNameValidation()
     {
        let element = document.getElementById("adminName");
        let error = document.getElementById("adminNameError");
        let nameRegex = /^[A-Za-z ]+$/;
        console.log(element);

        if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
            error.innerHTML = "";
            fieldsChecks["adminName"] = true;
        } else
        {
            error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 3 and less than 30.";
            error.style.color = "red";
            fieldsChecks["adminName"] = false;
        }
        validateAndEnableSubmit();
    }



 // departmentName
    function departmentNameValidation() {
          let element = document.getElementById("complaintType");
          let error = document.getElementById("departmentNameError");

 if (element.value !== "0") {
        // If a valid option is selected
        //if filled

        error.innerHTML = "";
        fieldsChecks["departmentName"] = true;
    } else {
        // If the default option is selected
      // if empty

        error.innerHTML = "Please select a department.";
        error.style.color = "red";
        fieldsChecks["departmentName"] = false;
    }

          validateAndEnableSubmit();

}


//email

function emailValidation() {
let element = document.getElementById("email");
let error = document.getElementById("emailError");

// Regular expression pattern for validating email address
let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// Check if the email is valid
if (emailRegex.test(element.value)) {
// Email is valid
error.innerHTML = "";
fieldsChecks["email"] = true;
} else {
// Email is invalid
error.innerHTML = "Please enter valid email address.";
error.style.color = "red";
fieldsChecks["email"] = false;
}

validateAndEnableSubmit();
}


//contactNumber

   function contactNumberValidation() {
   let element = document.getElementById("contactNumber");
   let error = document.getElementById("contactNumberError");
   let mobileRegex = /^\d{10}$/;

   if (mobileRegex.test(element.value)) {
   error.innerHTML = "";
   fieldsChecks["contactNumber"] = true;
   } else {
   error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
   error.style.color = "red";
   fieldsChecks["contactNumber"] = false;
   }
   validateAndEnableSubmit();
   }


   // Alternate Contact Number

   function alternateContactNumberValidation() {
   let element = document.getElementById("alternateContactNumber");
   let error = document.getElementById("altContactNbrError");
   let mobileRegex = /^\d{10}$/;

   if (mobileRegex.test(element.value)) {
   error.innerHTML = "";
   fieldsChecks["alternateContactNumber"] = true;
   } else {
   error.innerHTML = "Invalid alternative contact number. It should be exactly 10 digits.";
   error.style.color = "red";
   fieldsChecks["alternateContactNumber"] = false;
   }
   validateAndEnableSubmit();
   }


   // Agree

   function agreeValidation() {
   let element = document.getElementById("agree");
   let error = document.getElementById("agreeError");

   if (element.checked) {
   error.innerHTML = "";
   fieldsChecks["agree"] = true;
   } else {
   error.innerHTML = "Please agree to the terms.";
   error.style.color = "red";
   fieldsChecks["agree"] = false;
   }
   validateAndEnableSubmit();
   }


        <!-- ajax email validation-->

                function emailValidation() {
                                   console.log("Validate email");
                                   let email = document.getElementById("email").value;
                                   console.log(email);
                                   let error = document.getElementById("emailError");
                                   const request = new XMLHttpRequest();
                                   request.open("GET", "http://localhost:8080/SystemManagement/validateEmail/" + email);
                                   request.send();
                                   console.log(request);
                                   request.onload = function () {
                                       var ref = this.responseText;
                                       console.log(ref);
                                       error.innerHTML = ref;

                               <!--enable and disable submit button for ajaxemail validation-->
                                       if (ref === "") {
                                       fieldsChecks["email"] = true;
                                      } else {
                                      fieldsChecks["email"] = false;
                                      }

                                      validateAndEnableSubmit();
                                      }
                                   }

               function contactNumberValidation() {
                   console.log("Validate contact number");
                   let contactNumber = document.getElementById("contactNumber").value;
                   console.log(contactNumber);
                   let error = document.getElementById("contactNumberError");
                   const request = new XMLHttpRequest();
                   request.open("GET", "http://localhost:8080/SystemManagement/validateContactNumber/" + contactNumber);
                   request.send();
                   console.log(request);
                   request.onload = function () {
                       var ref = this.responseText;
                       console.log(ref);
                       error.innerHTML = ref;

                       <!-- Enable and disable submit button for AJAX contact number validation-->
                       if (ref === "") {
                           fieldsChecks["contactNumber"] = true;
                       } else {
                           fieldsChecks["contactNumber"] = false;
                       }

                       validateAndEnableSubmit();
                   }
                   request.onerror = function () {
                               console.error("Request failed");
                               error.innerHTML = "<span style='color:red;'>Validation failed. Please try again.</span>";
                               fieldsChecks["contactNumber"] = false;
                               validateAndEnableSubmit();
                           }
               }
