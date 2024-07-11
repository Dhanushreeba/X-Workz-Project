
package com.xworkz.project.controller;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.EditUserService;
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

@Controller
@RequestMapping("/")
@SessionAttributes("dto")
@Slf4j
public class EditController {

    private static final Logger log = LoggerFactory.getLogger(EditController.class);
    @Autowired
    private EditUserService editUserService;

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
    @PostMapping("/edit")
    public String editUser(SignUpDto dto, Model model) {
        SignUpDto updatedUser = editUserService.editByEmail(dto);
        if (updatedUser != null) {
            model.addAttribute("dto", updatedUser);
            model.addAttribute("successMessage", "Profile updated successfully");
            return "Edit";
        }
        model.addAttribute("errorMessage", "Error updating profile");
        return "ProfileUpload";
    }

}
