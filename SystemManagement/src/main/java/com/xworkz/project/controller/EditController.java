package com.xworkz.project.controller;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.EditUserService;
import com.xworkz.project.model.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@SessionAttributes({"userData"})
@Slf4j
public class EditController {

    //to upload an image to the profile page
    private static final String UPLOAD_DIR = "C:\\Users\\VARSHITHA\\Desktop\\X-Workz-Project\\imageEdit";

    private static final Logger log = LoggerFactory.getLogger(EditController.class);
    @Autowired
    private EditUserService editUserService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpSession httpSession;

    EditController(){
        EditController.log.info("Created constr for EditController");
    }

//    @GetMapping("/edits/{email}")
//    public String showEditForm(@PathVariable String email, Model model) {
//        SignUpDto user = editUserService.findByEmail(email);
//        String userEmail = (String) httpSession.getAttribute("signedInUserEmail");
//        model.addAttribute("dto", user);
//        return "Edit"; // JSP page name for editing profile
//    }
//
//    @PostMapping("/edit")
//    public String editUser(SignUpDto dto, Model model) {
//        SignUpDto updatedUser = editUserService.editByEmail(dto);
//        if (updatedUser != null) {
//            model.addAttribute("dto", updatedUser);
//            model.addAttribute("successMessage", "Profile updated successfully");
//            return "ProfileUpload"; // Redirect or forward to the profile page
//        }
//        model.addAttribute("errorMessage", "Error updating profile");
//        return "editProfile"; // Redirect back to the edit profile page
//    }

    //Edit
    @GetMapping("edits")
    public String showUserDetails(Model model) {
        String userEmail = (String) httpSession.getAttribute("signedInUserEmail");
        log.info("Signed-in user email: " + userEmail);

        if (userEmail != null) {
            SignUpDto dto = editUserService.findByEmail(userEmail);
            model.addAttribute("dto", dto);
        } else {
            log.info("User email not found in session.");
        }

        return "Edit";
    }

    //Edit
//    @PostMapping("/edit")
//    public String editUser(SignUpDto dto, Model model) {
//        SignUpDto updatedUser = editUserService.editByEmail(dto);
//        if (updatedUser != null) {
//            model.addAttribute("dto", updatedUser);
//            model.addAttribute("successMessage", "Profile updated successfully");
//            return "Edit";
//        }
//        model.addAttribute("errorMessage", "Error updating profile");
//        return "ProfileUpload";
//    }

    @PostMapping("/edit-profile") // In this image also uploading
    public String updateUserProfile(@RequestParam("file") MultipartFile file, SignUpDto dto, Model model, HttpSession session) {

        SignUpDto signUpDto = (SignUpDto) model.getAttribute("userData");
        System.out.println(" image upload user data "+signUpDto);

        try {
            String newFileName = null;
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                newFileName = signUpDto.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                log.info("Path: {}", path);
                Files.write(path, file.getBytes());
                dto.setImageName(newFileName);

                // Save the file to disk (optional)
//                String fileName = file.getOriginalFilename();
//                String filePath = "C:\\Users\\VARSHITHA\\Desktop\\X-Workz-Project\\imageEdit" + fileName;
//                file.transferTo(new File(filePath));


                // Save image details in database
                ImageDto imageDto = new ImageDto();
//                imageDto.setUser(dto); // Set the user
                imageDto.setImagePath(newFileName); // Set the image path
                imageDto.setImageName(originalFilename);
                imageDto.setImageSize(file.getSize());
                imageDto.setImageType(file.getContentType());
                imageDto.setCreatedBy(signUpDto.getEmail());
                imageDto.setCreatedOn(LocalDateTime.now());
                imageDto.setUpdatedBy(signUpDto.getEmail());
                imageDto.setUpdatedOn(LocalDateTime.now());
                imageDto.setUserId(signUpDto.getId());
                signUpDto.setImageName(newFileName);



                System.out.println(" image data "+imageDto);

                System.out.println("image name in signUp database"+signUpDto);

                boolean isSaved = imageService.saveImageDetails(imageDto);
                log.info("Image details saved: {}", isSaved);


            }

            SignUpDto updatedUser = editUserService.updateUser(signUpDto);
            if (updatedUser != null) {
                model.addAttribute("dto", updatedUser);
                model.addAttribute("successMessage", "Profile updated successfully :"+dto.getFirstName());
                httpSession.setAttribute("email", updatedUser.getEmail());
                httpSession.setAttribute("firstName", updatedUser.getFirstName());
                httpSession.setAttribute("lastName", updatedUser.getLastName());
                httpSession.setAttribute("contactNumber", updatedUser.getContactNumber());

                if (newFileName != null) {
                    String imageUrl = "/images/" + newFileName;
                    httpSession.setAttribute("profileImage", imageUrl);
                    model.addAttribute("imageURL", imageUrl);
                }

                // Display in console
                log.info("Image upload");
                log.info("file getName: {}", file.getName());
                log.info("file getContentType: {}", file.getContentType());
                log.info("file getResource: {}", file.getResource());
                log.info("file getOriginalFilename: {}", file.getOriginalFilename());
                log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());

                return "Edit"; // Redirect to edit profile page
            } else {
                model.addAttribute("message", "Profile update failed, User not found.");
            }
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            log.error("Error uploading file", e);
        }

        return "ProfileUpload";// Handle error or success case

    }


}
