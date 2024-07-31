package com.xworkz.project.model.service;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.DepartmentDto;
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

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        System.out.println("saveDepartment method running in AdminServiceImpl..");

        DepartmentDto data = adminRepo.saveDepartment(departmentDto);

        System.out.println("data:" + data);

        if (data != null) {
            System.out.println("saveDepartment  successful in AdminServiceImpl..");

            return data;
        } else {
            System.out.println("saveDepartment not successful in AdminServiceImpl..");
        }

        return null;
    }

    //find all Departments in dropdown where admin view details
    @Override
    public List<DepartmentDto> findAll(String departmentType) {
        System.out.println("findAll method is running in AdminServiceImpl..");
        List<DepartmentDto> data = adminRepo.findAll(departmentType);
        System.out.println("DepartmentName: " +data);

        if (data != null) {
            System.out.println("findAll successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("findAll  not successful in AdminServiceImpl..");
        }
        return null;

    }

    //update department id and Status
    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {
        //update status and department id

        System.out.println("updateStatusAndDepartmentId method running in AdminRepoImpl.. RaiseComplaintService");
        adminRepo.updateStatusAndDepartmentId(complaintId, departmentId, status);
    }

}
