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
        System.out.println("Search method is running in controller");
        System.out.println(raiseComplaintDto.getComplaintType()+"****8****"+raiseComplaintDto.getCity());
if( raiseComplaintDto.getCity()!=null &&  raiseComplaintDto.getComplaintType().isEmpty()){
    List<RaiseComplaintDto> list = adminService.searchByCity(raiseComplaintDto.getCity());
    model.addAttribute("complaintList", list);
    return  "ComplaintSearchByAdmin";

} else if (raiseComplaintDto.getComplaintType()!=null && raiseComplaintDto.getCity().isEmpty()
) {
    List<RaiseComplaintDto> list = adminService.searchByType(raiseComplaintDto.getComplaintType());
    model.addAttribute("complaintList", list);
    return  "ComplaintSearchByAdmin";
} else {

    List<RaiseComplaintDto> list = adminService.searchByTypeAndCity(
            raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());

    if (!list.isEmpty()) {
        System.out.println("Search in controller success: " + raiseComplaintDto);
        model.addAttribute("message", raiseComplaintDto.getComplaintType());
        model.addAttribute("complaintList", list);
        return "ComplaintSearchByAdmin";
    } else {
        System.out.println("Search in controller is not successful: " + raiseComplaintDto);
    }


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

//@GetMapping("/city-search")
//public String search(@Valid RaiseComplaintDto raiseComplaintDto, Model model)
//{
//    System.out.println("search method is running in searchController");
//    List<RaiseComplaintDto> list=adminService.searchByCity(raiseComplaintDto);
//    if(!list.isEmpty())
//    {
//        System.out.println("search in controller success"+raiseComplaintDto);
//    }
//    else {
//        System.out.println("search in controller not success"+raiseComplaintDto);
//        return "AdminSearchByCity";
//
//    }
//    model.addAttribute("complaint",raiseComplaintDto.getCity());
//    model.addAttribute("listOfComplaint",list);
//    return "AdminSearchByCity";
//}