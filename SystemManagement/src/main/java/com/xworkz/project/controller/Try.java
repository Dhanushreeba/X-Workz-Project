////view Raise complaint details
//@GetMapping("View-raise-complaint")
//public String viewRaiseComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model)
//{
//    System.out.println("viewUserDetails method running in AdminController");
//
//    List<RaiseComplaintDTO> viewData = adminService.findById(raiseComplaintDTO);
//    model.addAttribute("viewRaiseComplaint",viewData);
//    if(viewData!=null)
//    {
//        System.out.println("View raise complaint data successful in AdminController");
//        return "AdminViewRaiseComplaintDetails";
//    }
//
//    else
//    {
//        System.out.println("View raise complaint data not successful in AdminController.");
//    }
//    return "AdminViewRaiseComplaintDetails";
//}