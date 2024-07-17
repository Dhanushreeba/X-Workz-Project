package com.xworkz.project.model.repo;


import com.xworkz.project.dto.RaiseComplaintDto;

public interface RaiseComplaintRepo {

    boolean saveComplaintRaiseData(RaiseComplaintDto raiseComplaintDto);

   // RaiseComplaintDto userId(RaiseComplaintDto raiseComplaintDto);


}
