package com.xworkz.project.model.service;

import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.RaiseComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RaiseComplaintServiceImpl implements RaiseComplaintService{

    @Autowired
    private RaiseComplaintRepo raiseComplaintRepo;


    @Override
    public boolean saveComplaintRaiseData(RaiseComplaintDto raiseComplaintDto) {
        System.out.println("saveRaiseComplaintData method running RaiseComplaintServiceImpl");

        boolean data = raiseComplaintRepo.saveComplaintRaiseData(raiseComplaintDto);
        if (data) {
            System.out.println("saveRaiseComplaintData  successful in RaiseComplaintServiceImpl..");
            return true;
        } else {
            System.out.println("saveRaiseComplaintData no successful in RaiseComplaintServiceImpl..");
        }
        return false;
    }

    //view
    @Override
    public Optional<RaiseComplaintDto> findByUserId(int id) {
        return raiseComplaintRepo.findByUserId(id);
    }

//view
    @Override
    public Optional<RaiseComplaintDto> findBySignedInUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        SignUpDto signedInUser = (SignUpDto) httpSession.getAttribute("signUpDTO");
        if (signedInUser != null) {
            return raiseComplaintRepo.findByUserId(signedInUser.getId());
        }
        return Optional.empty();

    }

    //view
    @Override
    public List<RaiseComplaintDto> getComplaintsByUserId(int id) {
        return raiseComplaintRepo.findByRaise(id);
    }

    //Edit
    @Override
    public RaiseComplaintDto getComplaintById(int complaintId) {
        return raiseComplaintRepo.findByComplaintId(complaintId).orElse(null);
    }

    //update
//to set the signUpDTO id to stored id in
    @Override
    public List<RaiseComplaintDto> updateRaiseComplaintUserDetails(RaiseComplaintDto raiseComplaintDto) {
        RaiseComplaintDto complaint= this.raiseComplaintRepo.findByComplaintId(raiseComplaintDto.getComplaintId()).get();
        raiseComplaintDto.setDto(complaint.getDto());
        RaiseComplaintDto raiseComplaintDto1=raiseComplaintRepo.updateRaiseComplaintUserDetails(raiseComplaintDto);
        List<RaiseComplaintDto> dtos=this.raiseComplaintRepo.findByRaise(raiseComplaintDto1.getDto().getId());
        if(dtos!=null)
        {
            System.out.println("Data successfully updated");
            return  dtos;
        }
        else
        {
            System.out.println("Data is not successfully updated");
            return  null;
        }

    }

}

