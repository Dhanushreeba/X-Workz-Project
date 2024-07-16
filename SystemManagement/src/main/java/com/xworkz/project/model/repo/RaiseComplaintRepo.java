package com.xworkz.project.model.repo;


import com.xworkz.project.dto.RaiseComplaintDto;

public interface RaiseComplaintRepo {

    boolean saveImage(RaiseComplaintDto raiseComplaintDto);

    RaiseComplaintDto userId(RaiseComplaintDto raiseComplaintDto);

}
