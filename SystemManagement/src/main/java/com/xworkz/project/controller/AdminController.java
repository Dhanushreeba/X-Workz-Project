package com.xworkz.project.controller;


import com.xworkz.project.dto.*;
import com.xworkz.project.model.repo.AdminRepo;
import com.xworkz.project.model.service.AdminService;
import com.xworkz.project.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
     private AdminService adminService;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
     private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder encoder;

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
    public String viewUserRaiseDetails(RaiseComplaintDto raiseComplaintDto,DepartmentDto departmentDto, Model model) {
        System.out.println("viewUserRaiseDetails method in AdminController..");
        List<RaiseComplaintDto> raiseComplaintDtoData = adminService.getById(raiseComplaintDto);

        List<DepartmentDto> departments = adminService.findAll(departmentDto.getDepartmentName());

        model.addAttribute("viewRaiseComplaint",raiseComplaintDtoData);
        model.addAttribute("departments",departments);

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
    public String searchComplaintByTypeAndCity(@Validated RaiseComplaintDto raiseComplaintDto,DepartmentDto departmentDto, Model model) {
        System.out.println("searchByComplaintType method running in AdminController..!!" + "***************__________" + raiseComplaintDto);

        //after search the status and deparment id has to be visible in search so use below statement
        List<DepartmentDto> departments = adminService.findAll(departmentDto.getDepartmentName());

        if (!raiseComplaintDto.getComplaintType().isEmpty() && !raiseComplaintDto.getCity().isEmpty()) {
            List<RaiseComplaintDto> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
            // System.out.println("searchByComplaintTypeAndCity successful in AdminController");
            model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
            model.addAttribute("departments",departments);
            return "AdminViewRaiseComplaintDetails";
        }

        if (!raiseComplaintDto.getComplaintType().isEmpty() || !raiseComplaintDto.getCity().isEmpty()) {
            List<RaiseComplaintDto> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
            System.out.println("***********" + listOfTypeOrCity);
            //   System.out.println("searchByComplaintTypeOrCity ");
            model.addAttribute("viewRaiseComplaint", listOfTypeOrCity);
            model.addAttribute("departments",departments);
            return "AdminViewRaiseComplaintDetails";


        }

        else {
            model.addAttribute("msg", "no resultfound");
            return "AdminViewRaiseComplaintDetails";
        }

    }

    //save  department
    @PostMapping("department-add")
    public String saveDepartment(DepartmentDto departmentDto, Model model) {
        System.out.println("saveDepartment method running in AdminController..");

        DepartmentDto data = adminService.saveDepartment(departmentDto);
        List<DepartmentDto> departments = adminService.findAll(departmentDto.getDepartmentName());

        if (data != null) {
            System.out.println("saveDepartment successful in AdminController..");
            model.addAttribute("msg", "Successfully added department ");
            return "DepartmentAdding";

        } else {
            System.out.println("saveDepartment not successful in AdminController..");

            model.addAttribute("error", "not Successfully added department");
        }

        return "DepartmentAdding";
    }

    //update department id  and status

    @PostMapping("/update-department")
    public String updateComplaint(@RequestParam("complaintId") int complaintId,
                                  @RequestParam("departmentId") int departmentId,
                                  @RequestParam("status") String status,
                                  Model model) {
        adminService.updateStatusAndDepartmentId(complaintId, departmentId, status);
        model.addAttribute("successMessage", "Department allocated successfully!");
        return "redirect:/view-user-raise-complaint";
    }

    //****************************************************
    //Add Department Admin and saved in database(register page)

    @PostMapping("add-department-admin")
    public String addDepartmentAdmin(@Valid DepartmentAdminDto departmentAdminDto,DepartmentDto departmentDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,@RequestParam("email") String email,String password) {
        System.out.println("addDepartmentAdmin method running in AdminController..");

        //to save dep_id of departmentDto in departmentAdminDto
        DepartmentDto result =adminService.searchByDepartmentType(departmentAdminDto.getDepartmentName());
        System.out.println(departmentAdminDto.getDepartmentName()+"********"+result);
        departmentAdminDto.setDepartment(result);

        //generate automatic password encrypted
        String generatedPassword=passwordGenerator.generatePassword(12);

        //generate automatic password encrypted
        departmentAdminDto.setPassword(encoder.encode(generatedPassword));


        System.out.println("DepartmentAdminDto  : " + departmentAdminDto);

        if (bindingResult.hasErrors()) {
            System.out.println("DepartmentAdminDto has invalid data");
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("departmentAdminDto", departmentAdminDto); //this retaining values form page

            return "RegisterDepartmentAdmin";


        } else {
            boolean saveData = adminService.saveDepartmentAdminData(departmentAdminDto);
            if (saveData) {

                departmentAdminDto.setPassword(generatedPassword);

                //send email with generated password
                String subject="Welcome to our Issue Management";

                String body="Hello" + departmentAdminDto.getAdminName() + ",\n\n your password is successfull generated. your password is :" +departmentAdminDto.getPassword();
                adminService.sendingEmail(email,subject, body);


                System.out.println("saveDepartmentAdminData saved successful in addDepartmentAdmin");
                redirectAttributes.addFlashAttribute("msg", "Department Admin data saved successfully..");
                 model.addAttribute("msg", "Department Admin data saved successfully..");

                return "RegisterDepartmentAdmin";

            } else {
                System.out.println("saveDepartmentAdminData not saved successful..");
                redirectAttributes.addFlashAttribute("errorMsg", "Department Admin data not saved successfully..");
                //model.addAttribute("msg", "Department Admin data saved successfully..");

            }

            return "RegisterDepartmentAdmin";
        }
    }

    @PostMapping("sub-admin-log-in")
    public String subAdminLogin(DepartmentAdminDto departmentAdminDto,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("departmentName")String departmentName,Model model, HttpSession session) {
        System.out.println("subAdminLogin method running in AdminController..");
        DepartmentAdminDto login1=adminRepo.findByEmail(email);
      DepartmentAdminDto login = adminService.findEmailAndPasswordAndDepartment(email, password,departmentName);

        if (login != null && login!=null) {
            System.out.println("subAdminLogin successful AdminController..");
             session.setAttribute("departmentAdminDto",login);
            // adminService.resetFailedAttempts(email); //locked

            model.addAttribute("msg", "Login successful");
            return "SubAdminProfile";
        } else {
            System.out.println("subAdminLogin not successful in AdminController..");
            adminService.incrementFailedAttempts(email); //locked

            int failedAttempts = adminService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + " : " + failedAttempts);
            model.addAttribute("errorMsg", "Failed to login please check your email and password");

            if (failedAttempts >= 3) {
                adminService.lockAccount(email);// Lock account after 3 failed attempts
                System.out.println(email + " :Your account is locked due to too many failed attempts");

                model.addAttribute("accountError", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            } else {
                model.addAttribute("error", "Invalid email id and password. Attempts:" + failedAttempts);
                System.out.println("Invalid email Id and password");
                model.addAttribute("accountLocked", false);
            }

            return "SubAdminLogin";
        }
    }

    @PostMapping("sub-admin-forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        System.out.println("forgotPassword method running in AdminController..");

        DepartmentAdminDto resetEmail = adminService.resetPasswordEmail(email);

        if (resetEmail != null) {
            System.out.println("forgotPassword successful in AdminController..");
            model.addAttribute("msg", "A new password has been sent to your email.");

            // Reset failed attempts
            adminService.resetFailedAttempts(email);
            adminService.unlockAccount(email);

            return "SubAdminLogin";
        } else {
            System.out.println("forgotPassword not successful in AdminController..");
            model.addAttribute("errorMsg", "Email Address not found");
        }

        return "SubAdminForgetPassword";
    }


    @PostMapping("admin-reset")
    public String resetPassword(Model model,
                                @RequestParam("email") String email,
                                @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword) {
        DepartmentAdminDto departmentAdminDto = adminRepo.findByEmail(email);

        if (departmentAdminDto == null) {
            System.out.println("No user found with email: " + email);
            model.addAttribute("passwordResetError", "No user found with provided email.");
            return "SubAdminResetPassword";
        }

        String storedPassword = departmentAdminDto.getPassword();

        if (storedPassword == null) {
            System.out.println("No stored password for email: " + email);
            model.addAttribute("passwordResetError", "Stored password is missing for the user.");
            return "SubAdminResetPassword";
        }

        System.out.println("Hello...........");

        boolean resetSuccessful = adminService.resetPassword(email, oldPassword, newPassword, confirmPassword);

        System.out.println("Thank You..........");

        if (!encoder.matches(oldPassword, storedPassword)) {
            System.out.println("Old password verification failed for email: " + email);
            return "SubAdminLogin"; // Old password doesn't match
        }

        System.out.println(departmentAdminDto.getPassword() + "**********");

        if (resetSuccessful) {
            System.out.println("Password reset Successful: " + resetSuccessful);
            model.addAttribute("passwordResetMessage", "Password reset successful");
        } else {
            model.addAttribute("passwordResetError", "Failed to reset password. Please check your password.");
        }

        return "SubAdminResetPassword";
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

    @GetMapping("/DepartmentAdding")
    public  String DepartmentAdd(){
         return "DepartmentAdding";
    }

    @GetMapping("/RegisterDepartmentAdmin")
    public  String RegisterDepartmentAdminPage(){
        return "RegisterDepartmentAdmin";
    }

    @GetMapping("/SubAdminLogin")
    public  String SubAdminLoginPage(){
        return "SubAdminLogin";
    }

    @GetMapping("/SubAdminProfile")
    public  String SubAdminProfilePage(){
        return "SubAdminProfile";
    }

    @GetMapping("/SubAdminForgetPassword")
    public  String SubAdminForgetPasswordPage(){
        return "SubAdminForgetPassword";
    }

    @GetMapping("/SubAdminResetPassword")
    public String SubAdminResetPasswordPage(){

        return "SubAdminResetPassword";
    }
}
