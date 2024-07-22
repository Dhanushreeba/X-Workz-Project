package com.xworkz.project.controller;

import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.SignUpRepo;
import com.xworkz.project.model.service.RaiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class RaiseComplaintController {

    @Autowired
    private RaiseComplaintService raiseComplaintService;

    @Autowired
            private SignUpRepo signUpRepo;

    RaiseComplaintController(){

        System.out.println("created constr for RaiseComplaintController");
    }

    @RequestMapping("/RaiseComplaintPage")
    public String RaiseComplaintPage(){

        return "RaiseComplaint";
    }

    @RequestMapping("/EditRaiseComplaintPage")
    public String EditRaiseComplaintPage(){

        return "EditRaiseComplaint";
    }



    @RequestMapping("/raise")
    public String raiseComplaint(RaiseComplaintDto raiseComplaintDto, Model model, RedirectAttributes redirectAttributes, @ModelAttribute("dto") SignUpDto dto ){
        System.out.println("raiseComplaint method running in RaiseComplaintController..");



//        log.info("RaiseComplaintsDTO: {}", raiseComplaintDTO);
        System.out.println("RaiseComplaintController: " +raiseComplaintDto);

        SignUpDto userid= new SignUpDto();
        userid.setId(dto.getId());
             raiseComplaintDto.setDto(userid);
        System.out.println("*************************");
        boolean dataValid = raiseComplaintService.saveComplaintRaiseData(raiseComplaintDto);
        System.out.println(dataValid);

        if (dataValid) {
            System.out.println("RaiseComplaintService registration successful in RaiseComplaintController.");
            model.addAttribute("raiseComplaintMsg", " RaiseComplaint Registration Successful : " + raiseComplaintDto.getComplaintId());
            return "RaiseComplaint";


        } else {
            System.out.println("RaiseComplaintService registration not successful in RaiseComplaintController..");
            model.addAttribute("raiseComplaintMsg", " RaiseComplaint Registration failed ");
            return "RaiseComplaint";
        }

    }

    //view RaiseComplaint
    @GetMapping("view-raiseComplaint")
    public String viewRaiseComplaint(Model model, @ModelAttribute("dto") SignUpDto dto, @RequestParam("id")int id) {
        System.out.println("viewing..........."+dto);
//        int id = dto.getId();
        System.out.println("getting id............"+id);
        List<RaiseComplaintDto> complaints = raiseComplaintService.getComplaintsByUserId(id);
        System.out.println("print complaint...."+complaints);
        model.addAttribute("ViewUserRaisedComplaints", complaints);
        return "ViewUserRaisedComplaint";
    }


    //edit Raise_Complaint
    @GetMapping("/edit-complaint/{complaintId}")
    public String showEditComplaintForm(@PathVariable("complaintId") int complaintId, Model model) {
        RaiseComplaintDto raiseComplaintDto = raiseComplaintService.getComplaintById(complaintId);
        model.addAttribute("raiseComplaintDto", raiseComplaintDto);//values should be retain in page
        return "EditRaiseComplaint";
    }

}
