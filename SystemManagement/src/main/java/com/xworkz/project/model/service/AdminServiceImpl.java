package com.xworkz.project.model.service;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.AdminRepo;
import com.xworkz.project.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepo adminRepo;


    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDto data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDto> findById(SignUpDto dto) {
        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDto> dtoData=  adminRepo.findById(dto);
        if(dtoData!=null)
        {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        }
        else
        {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto) {
        System.out.println("getById method in AdminServiceImpl..");
        List<RaiseComplaintDto> raiseComplaintDtoData=  adminRepo.getById(raiseComplaintDto);
        if(raiseComplaintDtoData!=null)
        {
            System.out.println("getById data successful in AdminServiceImpl..");
            return raiseComplaintDtoData;
        }
        else
        {
            System.out.println("getById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }


//    //searchByType search operation
//    @Override
//    public List<RaiseComplaintDto> searchByType(String complaintType) {
//        System.out.println("Running search By Type in Service implementation");
//        List<RaiseComplaintDto> list = this.adminRepo.searchByType(complaintType);
//        if (!list.isEmpty()) {
//            System.out.println("Search result for complaint type: " + complaintType);
//            return list;
//        } else {
//            System.out.println("No complaints found for complaint type: " + complaintType);
//        }
//        return Collections.emptyList();
//    }

//    //search by city
//    @Override
//    public List<RaiseComplaintDto> searchByCity(String city) {
//        System.out.println("Running searchByCity in Service implementation");
//        List<RaiseComplaintDto> list = this.adminRepo.searchByCity(city);
//        if (!list.isEmpty()) {
//            System.out.println("Search result for city: " + city);
//            return list;
//        } else {
//            System.out.println("No complaints found for city: " + city);
//        }
//        return Collections.emptyList();
//    }

    //search by type and city
    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("Running search By Type and city in Service implementation");
        List<RaiseComplaintDto> list = this.adminRepo.searchByComplaintTypeAndCity(complaintType,city);
        if (!list.isEmpty()) {
            System.out.println("Search result for complaint type And city: " + complaintType);
            return list;
        } else {
            System.out.println("No complaints found for complaint type And city : " + complaintType);
            return Collections.emptyList();
        }

    }

    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl..");

        List<RaiseComplaintDto> listOfData = adminRepo.searchByComplaintTypeOrCity(complaintType, city);
        if (!listOfData.isEmpty()) {
            System.out.println("searchComplaintTypeAndCity successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchByComplaintTypeAndCity not successful in AdminServiceImpl..");
            return Collections.emptyList();
        }


    }

}
