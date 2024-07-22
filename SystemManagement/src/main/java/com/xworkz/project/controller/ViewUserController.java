package com.xworkz.project.controller;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
@SessionAttributes("dto")
public class ViewUserController {

    @Autowired
     private SignUpService signUpService;

    @Autowired
    private HttpSession httpSession; // Autowire the HttpSession

    ViewUserController() {
        System.out.println("created constr for ViewUserController");
    }

    @GetMapping("view") //we have to call this action in profile (link)
    public String showUserDetails(Model model) {
        // Fetch the signed-in user's email from the session
        String userEmail = (String) httpSession.getAttribute("signedInUserEmail");

        // Debugging output to verify the email
        System.out.println("Signed-in user email: " + userEmail);

        if (userEmail != null) {
            // Fetch user data based on the email
            SignUpDto dto = signUpService.getUserByEmail(userEmail);
            // Add the user data to the model
            model.addAttribute("dto", dto);
        } else {
            System.out.println("User email not found in session.");
        }

        // Return the view name
        return "ViewUserPage";
    }


}