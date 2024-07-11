package com.xworkz.project.controller;


import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.SignUpService;
import com.xworkz.project.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
@Slf4j
public class ForgetPasswordController {

    private static final Logger log = LoggerFactory.getLogger(ForgetPasswordController.class);
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private PasswordGenerator passwordGenerator;

    public ForgetPasswordController() {
        System.out.println("Created constructor for ForgetPasswordController");
    }

//    //this is  after forget password
//    @PostMapping("/forget")
//    public String run(@Valid SignUpDto dto, BindingResult bindingResult, Model model, @RequestParam("email") String email) {
//        // Generate and set password before validation
//        String generatedPassword = passwordGenerator.generatePassword(12);
//        dto.setPassword(generatedPassword);
//
//        if (bindingResult.hasErrors()) {
//            System.out.println("Invalid data provided for SignUpController in run method of forgetPassword");
//            bindingResult.getAllErrors().forEach(dtoError -> System.out.println(dtoError.getDefaultMessage()));
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            model.addAttribute("dto", dto);
//            return "ForgetPassword"; // Return the name of the forget password view
//        } else {
//            model.addAttribute("msg", "SignUp Successful " + dto.getEmail());
//            boolean save = this.signUpService.save(dto);
//            System.out.println("dto" + dto);
//            if (save) {
//                System.out.println("Details Saved Successfully " + dto);
//
//                // Send email with generated password
//                String subject = "Welcome to our Issue Management";
//                String body = "Hello " + dto.getEmail() + ",\n\nYour new password has been generated for you: " + dto.getPassword();
//
//                signUpService.sendingEmail(email, subject, body);
//               return "WelcomePage";
//            } else {
//                System.out.println("Details Not Saved Successfully " + dto);
//                return "ForgetPassword"; // Return the name of the forget password view
//            }
//        }
//        System.out.println("Creating ForgetPassword page/ForgetPassword");
//        System.out.println("ForgetPassword Data :" + dto);
//        return "WelcomePage"; // Return the name of the welcome page view


    //Using @RequestParam annotation im fetching only the fields of email and password because,
    //here in forget password im only generating  password to an existing email so i used this
    @PostMapping("/forget")
    public String run(@RequestParam("email") String email, Model model) {
        // Create a new SignUpDto and set only the email
        SignUpDto dto= signUpService.findByEmail(email);//here y i use findByEmail is to generate password for existing email and it has to be stored in database

        if (email == null) {
            ForgetPasswordController.log.info("No user found with email: " + email);
            model.addAttribute("error", "No user found with this email." +dto.getEmail());
            return "ForgetPassword"; // Or the appropriate view name
        }else{
            log.info("user found with this password :"+dto.getEmail());
        }

        // Generate and set password before saving
        String generatedPassword = passwordGenerator.generatePassword(12);
        dto.setPassword(generatedPassword);

        model.addAttribute("msg", "new password Successfully generated " + dto.getEmail());
        boolean save = this.signUpService.save(dto);
        if (save) {
            log.info("Details Saved Successfully " + dto);

            // Send email with generated password
            String subject = "Welcome to our Issue Management";
            String body = "Hello " + dto.getEmail() + ",\n\nA new password has been generated for you: " + dto.getPassword();

            signUpService.sendingEmail(email, subject, body);
            return "WelcomePage";
        } else {
            log.info("Details Not Saved Successfully " + dto);
            model.addAttribute("error", "Failed to save details");
            return "ForgetPassword"; // Return the name of the forget password view
        }

    }



}

