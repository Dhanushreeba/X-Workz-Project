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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//to map actions we use requestmapping and / will use to map actions ,@Controller tell to spring container to handel the controller and to create the object for this particular class
//where we use @controller in class is nothing but a  create object for that particular class
@Controller
@RequestMapping("/")
@Slf4j
public class SignUpController {

    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
    //SystemService will give the repository(implementation class) queries to the controller class
    @Autowired
    private SignUpService signUpService;

    //getting refrence from spring container,association,initialzation
    //passwordGenerator is to generate a random password
    @Autowired
    private PasswordGenerator passwordGenerator;

    //View
    @Autowired
    private HttpSession httpSession;


    public SignUpController() {
        log.info("Created constructor for SignUpController");
    }

    //this display method is for Signup form and signUp.jsp
    //@validate
    //BindingResult
    //Model
    //@RequestParam
    @PostMapping("/signup")
    public String display(@Valid SignUpDto dto, BindingResult bindingResult, Model model, @RequestParam("email") String email) {
        // Generate and set password before validation
        String generatedPassword =passwordGenerator.generatePassword(12);
        dto.setPassword(generatedPassword);

        if (bindingResult.hasErrors()) {
            log.info("Dto has invalid data in SignUpController");
            bindingResult
                    .getAllErrors()
                    .forEach(dtoError -> System.out.println(dtoError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dto", dto);
            return "SignUp";
        } else {
            model.addAttribute("name", "SignUp Successful " + dto.getFirstName());
            boolean save = this.signUpService.save(dto);
            log.info("dto" + dto);
            if (save) {
                System.out.println("Details Saved Successfully " + dto);

                //send email with generated password
                String subject="Welcome to our Issue Management";

                String body="Hello" + dto.getFirstName() + ",\n\n your registration is successfull. your password is :" +dto.getPassword();
                signUpService.sendingEmail(email,subject, body);

            } else {
                log.info("Details Not Saved Successfully " + dto);
                return "SignIn";
            }
        }
        model.addAttribute("name", "SignUp Successful " + dto.getFirstName());
        log.info("Creating SignUp page/SignUp");
        log.info("SignUp Data :" + dto);
        return "SignIn";
    }

    //LoginPage
//    @GetMapping("/login")
//    public String loginPage(Model model) {
//        model.addAttribute("dto", new SystemDto());
//        return "Login";
//    }


    //this login method is for Signin form and signin.jsp
    @PostMapping("/signin")
    public String signin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        log.info("running signIn method...");
        log.info("Email :" + email);
        log.info("password :" + password);
        SignUpDto dto = signUpService.findByEmailAndPassword(email, password);
        if (dto != null) {
            //System.out.println("login success");
            signUpService.resetFailedAttempts(email);
            model.addAttribute("msg", "SignIn successful " + dto.getFirstName());
            //view
            httpSession.setAttribute("signedInUserEmail",email);

            return "ProfileUpload";//change to welcome page
        } else {
            //the else part is set account lock
            //acc lock after 3 attempts
            signUpService.incrementFailedAttempts(email);
            int failedAttempts = signUpService.getFailedAttempts(email);
            log.info("Failed attempts for " + email + ": " + failedAttempts);
            if (failedAttempts >= 3) {
                signUpService.lockAccount(email);
                log.info(email + "Your account is locked due to too many failed attempts.");
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accLocked", true); // Add attribute to indicate the account is locked

            } else {
                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                log.info("Invalid email id and password");
                model.addAttribute("accLocked", false); // Add attribute to indicate the account is not locked

            }
            return "SignIn"; // Return to login page
        }

        //System.out.println("login not success");
        // model.addAttribute("error","Invalid email or password. Please try again");
        //  return "Login";


//
//    @PostMapping()
//    public String sendEmail(String to, String subject, String text, Model model) {
//        try {
//            systemService.sendingEmail(to, subject, text);
//            model.addAttribute("msg", "Email sent successfully!");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error sending email: " + e.getMessage());
//        }
//        return "email.jsp";
//    }

    }
}

