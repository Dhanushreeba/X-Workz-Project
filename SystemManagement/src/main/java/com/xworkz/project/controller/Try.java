//package com.xworkz.project.controller;
//
//public class Try {
//
//
//    function contactNumberValidation() {
//        console.log("Validate contact number");
//        let contactNumber = document.getElementById("contactNumber").value;
//        console.log(contactNumber);
//        let error = document.getElementById("contactNumberError");
//
//    const request = new XMLHttpRequest();
//        request.open("GET", "http://localhost:8080/SystemManagement/validateContactNumber/" + contactNumber, true); // Use true for asynchronous request
//        request.send();
//
//        request.onload = function () {
//            var ref = this.responseText;
//            console.log(ref);
//            error.innerHTML = ref;
//
//            // Enable and disable submit button for AJAX contact number validation
//            if (ref === "") {
//                fieldsChecks["contactNumber"] = true;
//            } else {
//                fieldsChecks["contactNumber"] = false;
//            }
//
//            validateAndEnableSubmit();
//        }
//
//        request.onerror = function () {
//            console.error("Request failed");
//            error.innerHTML = "<span style='color:red;'>Validation failed. Please try again.</span>";
//            fieldsChecks["contactNumber"] = false;
//            validateAndEnableSubmit();
//        }
//    }
//
//}
