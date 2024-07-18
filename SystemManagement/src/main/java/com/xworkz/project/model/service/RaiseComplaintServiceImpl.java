package com.xworkz.project.model.service;

import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.model.repo.RaiseComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

