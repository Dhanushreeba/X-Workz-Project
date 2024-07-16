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


//import com.xworkz.project.constant.Status;
//import com.xworkz.project.dto.ImageDto;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;try {
//String newFileName = null;
//            if (file != null && !file.isEmpty()) {
//String originalFilename = file.getOriginalFilename();
//newFileName = signUpDto.getEmail() + "_" + originalFilename;
//Path path = Paths.get(UPLOAD_DIR, newFileName);
//                log.info("Path: {}", path);
//                Files.write(path, file.getBytes());
//        dto.setImageName(newFileName);
//
//// Save image details in database
//ImageDto imageDto = new ImageDto();
//                imageDto.setImagePath(newFileName);
//                imageDto.setImageName(originalFilename);
//                imageDto.setImageSize(file.getSize());
//        imageDto.setImageType(file.getContentType());
//        imageDto.setCreatedBy(signUpDto.getEmail());
//        imageDto.setCreatedOn(LocalDateTime.now());
//        imageDto.setUpdatedBy(signUpDto.getEmail());
//        imageDto.setUpdatedOn(LocalDateTime.now());
//        imageDto.setUserId(signUpDto.getId());
//        signUpDto.setImageName(newFileName);
//
//// Set all existing images to INACTIVE
//                imageService.setAllImagesInactiveForUser(signUpDto.getId());
//        log.info("All existing images set to INACTIVE for user ID: {}", signUpDto.getId());
//
//        // Set new image to ACTIVE
//        imageDto.setStatus(Status.ACTIVE);
//
//boolean isSaved = imageService.saveImageDetails(imageDto);
//                log.info("Image details saved: {}", isSaved);
//            }
//                    } catch (Exception e) {
//        e.printStackTrace();
//// Handle exceptions
//        }
//
//
