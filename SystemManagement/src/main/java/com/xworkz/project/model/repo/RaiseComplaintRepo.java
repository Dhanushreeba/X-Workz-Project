package com.xworkz.project.model.repo;


import com.xworkz.project.dto.RaiseComplaintDto;


import java.util.List;
import java.util.Optional;

public interface RaiseComplaintRepo {

    boolean saveComplaintRaiseData(RaiseComplaintDto raiseComplaintDto);

   // RaiseComplaintDto userId(RaiseComplaintDto raiseComplaintDto);

    Optional<RaiseComplaintDto> findByUserId(int id);

    //used for view raise complaint
    //view
    List<RaiseComplaintDto> findByRaise(int id);

    //edit
    public Optional<RaiseComplaintDto> findByComplaintId(int complaintId) ;

    //update
    RaiseComplaintDto updateRaiseComplaintUserDetails(RaiseComplaintDto raiseComplaintDto);
}
