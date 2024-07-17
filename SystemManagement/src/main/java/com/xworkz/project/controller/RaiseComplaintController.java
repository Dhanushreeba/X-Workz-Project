package com.xworkz.project.controller;

import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.model.service.RaiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RaiseComplaintController {

    @Autowired
    private RaiseComplaintService raiseComplaintService;

    RaiseComplaintController(){

        System.out.println("created constr for RaiseComplaintController");
    }

    @PostMapping("/raise-complaint")
    public String raiseComplaint(RaiseComplaintDto raiseComplaintDto, Model model) {
        System.out.println("raiseComplaint method running in RaiseComplaintController..");



//        log.info("RaiseComplaintsDTO: {}", raiseComplaintDTO);
        System.out.println("RaiseComplaintController: " +raiseComplaintDto);

        boolean dataValid = raiseComplaintService.saveComplaintRaiseData(raiseComplaintDto);
        System.out.println(dataValid);

        if (dataValid) {
            System.out.println("RaiseComplaintService registration successful in RaiseComplaintController.");
            model.addAttribute("raiseComplaintMsg", " RaiseComplaint Registration Successful : " + raiseComplaintDto.getComaplaintId());

             return "ProfileUpload";


        } else {
            System.out.println("RaiseComplaintService registration not successful in RaiseComplaintController..");
            model.addAttribute("raiseComplaintMsg", " RaiseComplaint Registration failed ");

        }

        return "ProfileUpload";
    }

}
