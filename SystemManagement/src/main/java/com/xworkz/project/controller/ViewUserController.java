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

//    @PostMapping("/viewUser")
//    public String view(@Valid SignUpDto dto, BindingResult bindingResult, Model model, @RequestParam("email") String email) {
//
//        if (bindingResult.hasErrors()) {
//            System.out.println("Dto has invalid data in ViewUserController");
//            bindingResult
//                    .getAllErrors()
//                    .forEach(dtoError -> System.out.println(dtoError.getDefaultMessage()));
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            model.addAttribute("dto", dto);
//            return "ProfileUpload";
//        } else {
//            model.addAttribute("name", "SignUp Successful " + dto.getFirstName());
//            boolean save = this.signUpService.save(dto);
//            System.out.println("dto" + dto);
//            if (save) {
//                System.out.println("Details Saved Successfully " + dto);
//
//                //send email with generated password
//                String subject = "Welcome to our Issue Management";
//
//                String body = "Hello" + dto.getFirstName() + ",\n\n Viwed successfully  :" + dto.getFirstName();
//                signUpService.sendingEmail(email, subject, body);
//
//            } else {
//                System.out.println("Details Not Saved Successfully " + dto);
//                return "View(details)";
//            }
//        }
//        model.addAttribute("name", "Viewed Successful " + dto.getFirstName());
//        System.out.println("Creating View page/View");
//        System.out.println("SignUp Data :" + dto);
//        return "View(details)";
//    }


//        @GetMapping("/getUserByEmail")
//        public String getUserByEmail(@RequestParam("email") String email, Model model) {
//            SignUpDto user = signUpService.findByEmail(email);
//            if (user != null) {
//                model.addAttribute("dto", user);
//                return "View(deatils)"; // Assume ProfileView.jsp is the page to view user details
//            } else {
//                model.addAttribute("error", "No user found with email: " + email);
//                return "ProfileUpload"; // Assuming ProfileUpload.jsp is the page where you can upload user details
//            }
//        }
//
//        @PostMapping("/saveUser")
//        public String saveUser(@Valid SignUpDto dto, BindingResult bindingResult, Model model) {
//            if (bindingResult.hasErrors()) {
//                System.out.println("Dto has invalid data in ViewUserController");
//                bindingResult
//                        .getAllErrors()
//                        .forEach(dtoError -> System.out.println(dtoError.getDefaultMessage()));
//                model.addAttribute("errors", bindingResult.getAllErrors());
//                model.addAttribute("dto", dto);
//                return "ProfileUpload";
//            } else {
//                boolean save = this.signUpService.save(dto);
//                if (save) {
//                    System.out.println("Details Saved Successfully " + dto);
//                    String subject = "Welcome to our Issue Management";
//                    String body = "Hello " + dto.getFirstName() + ",\n\nYour details have been saved successfully.";
//                    signUpService.sendingEmail(dto.getEmail(), subject, body);
//                    model.addAttribute("message", "SignUp Successful " + dto.getFirstName());
//                } else {
//                    System.out.println("Details Not Saved Successfully " + dto);
//                    model.addAttribute("error", "Details not saved. Please try again.");
//                    return "ProfileUpload";
//                }
//            }
//            return "View(deatils)"; // Redirect to view page after saving
//        }


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