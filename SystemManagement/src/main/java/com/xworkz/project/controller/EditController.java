
package com.xworkz.project.controller;

import com.xworkz.project.dto.ImageDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.EditUserService;
import com.xworkz.project.model.service.ImageService;
import com.xworkz.project.model.service.ImageServiceImpl;
import com.xworkz.project.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@SessionAttributes("dto")
@Slf4j
public class EditController {

    //to upload an image to the profile page
    private static final String UPLOAD_DIR = "C:\\Users\\VARSHITHA\\Desktop\\ImageUpload/";

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
    public String updateUserProfile(SignUpDto dto, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
        try {
            String newFileName = null;
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                newFileName = dto.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                log.info("path: {}", path);
                Files.write(path, file.getBytes());
                dto.setImageName(newFileName);

                // Save image details in database
                ImageDto imageDto = new ImageDto();
                imageDto.setUser(dto); // Set the user
                imageDto.setImagePath(newFileName); // Set the image path
                imageDto.setImageName(originalFilename);
                imageDto.setImageSize(file.getSize());
                imageDto.setImageType(file.getContentType());
                imageDto.setCreatedBy(dto.getEmail());
                imageDto.setCreatedOn(LocalDateTime.now());
                imageDto.setUpdatedBy(dto.getEmail());
                imageDto.setUpdatedOn(LocalDateTime.now());

                imageService.saveImageDetails(imageDto);
            }

            SignUpDto updatedUser = editUserService.editByEmail(dto);
            if (updatedUser != null) {
                model.addAttribute("dto", updatedUser);
                model.addAttribute("successMessage", "Profile updated successfully");
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
