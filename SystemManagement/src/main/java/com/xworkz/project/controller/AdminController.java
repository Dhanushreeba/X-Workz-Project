package com.xworkz.project.controller;


import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @GetMapping("view-user-raise-complaint")
    public String viewUserRaiseDetails(RaiseComplaintDto raiseComplaintDto, Model model) {
        System.out.println("viewUserRaiseDetails method in AdminController..");
        List<RaiseComplaintDto> raiseComplaintDtoData = adminService.getById(raiseComplaintDto);
        model.addAttribute("viewRaiseComplaint",raiseComplaintDtoData);

        if (raiseComplaintDtoData != null) {
            System.out.println("view-user-raise-details successful in AdminController..");
            model.addAttribute("viewUserRaiseDetails",raiseComplaintDtoData);
            return "AdminViewRaiseComplaintDetails";


        }
        else
        {
            System.out.println("view-user-raise-details not  successful in AdminController..");
        }
        return "AdminViewRaiseComplaintDetails";
    }



    @PostMapping("searchBy")
    public String searchComplaintByTypeAndCity(@Validated RaiseComplaintDto raiseComplaintDto, Model model) {
        System.out.println("searchByComplaintType method running in AdminController..!!"+"***************__________"+raiseComplaintDto);


        if(!raiseComplaintDto.getComplaintType().isEmpty() && !raiseComplaintDto.getCity().isEmpty()) {
            List<RaiseComplaintDto> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
                // System.out.println("searchByComplaintTypeAndCity successful in AdminController");
                model.addAttribute("raiseComplaint", listOfTypeAndCity);
                return "ComplaintSearchByAdmin";
            }

        if (!raiseComplaintDto.getComplaintType().isEmpty() && raiseComplaintDto.getCity().isEmpty()) {
            List<RaiseComplaintDto> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
            System.out.println("***********" + listOfTypeOrCity);
                //   System.out.println("searchByComplaintTypeOrCity ");
                model.addAttribute("raiseComplaint", listOfTypeOrCity);
                return "ComplaintSearchByAdmin";


        }

        if (!raiseComplaintDto.getCity().isEmpty() && raiseComplaintDto.getComplaintType().isEmpty()) {

            List<RaiseComplaintDto> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
            System.out.println("***********" + listOfTypeOrCity);

                model.addAttribute("raiseComplaint", listOfTypeOrCity);
                return "ComplaintSearchByAdmin";

        }else{
            model.addAttribute("msg", "no resultfound");
            return "ComplaintSearchByAdmin";
        }


    }


    @GetMapping("/AdminPage")
    public String AdminPage(){
        return "Admin";
    }

    @GetMapping("/AdminProfilePage")
    public String AdminProfilePage(){
        return "AdminProfile";
    }

    @GetMapping("/ComplaintSearchByAdminPage")
    public String ComplaintSearchByAdminPage(){
        return "ComplaintSearchByAdmin";
    }

}
