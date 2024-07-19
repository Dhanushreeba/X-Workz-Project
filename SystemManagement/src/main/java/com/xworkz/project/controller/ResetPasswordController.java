package com.xworkz.project.controller;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.SignUpRepo;
import com.xworkz.project.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@Slf4j
public class ResetPasswordController {

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);
    @Autowired
    private SignUpService signUpService;
@Autowired
private SignUpRepo singUpRepo;
    @Autowired
     private PasswordEncoder encoder;

    ResetPasswordController() {
        log.info("created constr for ResetPasswordController");
        log.info("Loggers for reset constr");
    }

    @GetMapping("/ResetPasswordPage")
    public String ResetPasswordPage(){
        return "ResetPassword";
    }


    @PostMapping("/reset")
    public String passwordReset(Model model, String email, String oldPassword, String newPassword, String confirmPassword) {
        SignUpDto dto= singUpRepo.lookByEmail(email);
        String storedPassword= dto.getPassword();
        System.out.println("hi...........");
        boolean resetSuccessful = signUpService.resetPassword(email, oldPassword, newPassword, confirmPassword);
        System.out.println("bye..........");
        // Step 3: Verify oldPassword matches the stored password
        if (!encoder.matches(oldPassword, storedPassword)){
            System.out.println("Old password verification failed for email: " +email);
            return "dto"; // Old password doesn't match
        }

        // Step 4: Encode and update the new password in SignupDto
       // dto.setPassword(encoder.encode(newPassword));
        System.out.println(dto.getPassword()+"**********");


        // Step 5: Save the updated password in the repository
      //  boolean save = SignUpRepo.updatePassword(email, ;

        // Step 6: Send email notification if password update was successful
        if (resetSuccessful) {
            log.info("Password reset Successful:" + resetSuccessful);
            model.addAttribute("passwordResetMessage", "Password reset successful");

        } else {
            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
        }

        return "ResetPassword";
    }
    // return "ResetPassword";
}

//    @PostMapping("/reset")
//    public String passwordReset(RedirectAttributes redirectAttributes, String email, String oldPassword, String newPassword, String confirmPassword) {
//
//        boolean resetSuccessful = signUpService.resetPassword(email, oldPassword, newPassword, confirmPassword);
//        if (resetSuccessful) {
//            System.out.println("Password reset Successful: " + resetSuccessful);
//            redirectAttributes.addFlashAttribute("passwordResetMessage", "Password reset successful");
//        } else {
//            redirectAttributes.addFlashAttribute("passwordResetError", "Failed to reset password. Please check your password");
//        }
//
//        return "reset";
//    }
//
//    @RequestMapping("reset")
//    public String showResetPasswordPage(Model model) {
//        return "ResetPassword";
//    }

