package com.xworkz.project.controller;


import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
     private AdminService adminService;

     AdminController(){
         System.out.println("created constr for AdminController");
     }

    @PostMapping("admin-controller")
    public String adminDetails(AdminDto adminDto, @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        System.out.println("adminDetails method in AdminController");


        boolean data = adminService.findByEmailAndPassword(email, password);

        if (data) {
            System.out.println("findByEmailAndPassword successful in AdminController..");
            model.addAttribute("adminMessage", "Login successful");

            model.addAttribute("AdminProfilePageMessage","Welcome to Admin profile");
            return "AdminProfile";
            //return "redirect:/adminPage";
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminController");
            model.addAttribute("errorAdminMessage", "Failed to login. Please check your email and password.");
            return "Admin";
        }
    }

    //view user details
    @GetMapping("view-user-details")
    public String viewUserDetails(SignUpDto dto, Model model) {
        System.out.println("viewUserDetails method in AdminController..");
        List<SignUpDto> signUpDtoData = adminService.findById(dto);

        if (signUpDtoData != null) {
            System.out.println("view-user-details successful in AdminController..");
            model.addAttribute("ViewUserDetails",signUpDtoData);
            return "AdminViewUserDetails";


        }
        else
        {
            System.out.println("view-user-details not  successful in AdminController..");
        }
        return "AdminViewUserDetails";
    }

    @GetMapping("/AdminPage")
    public String AdminPage(){
        return "Admin";
    }

    @GetMapping("/AdminProfilePage")
    public String AdminProfilePage(){
        return "AdminProfile";
    }

}
