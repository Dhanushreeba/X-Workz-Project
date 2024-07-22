package com.xworkz.project.model.service;

import com.xworkz.project.dto.RaiseComplaintDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface RaiseComplaintService {


    boolean saveComplaintRaiseData(RaiseComplaintDto raiseComplaintDto);

    //find user id and stored in raiseComplaint table
    public Optional<RaiseComplaintDto> findByUserId(int id);

    public Optional<RaiseComplaintDto> findBySignedInUser(HttpServletRequest request);


    //used for view raised complaint

    List<RaiseComplaintDto> getComplaintsByUserId(int id);
}
