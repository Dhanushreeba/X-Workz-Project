package com.xworkz.project.model.repo;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.DepartmentDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepoImpl implements AdminRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    AdminRepoImpl(){

        System.out.println("constr for AdminRepoImpl class");
    }

//Login page
    @Override
    public AdminDto findByEmailAndPassword(String email, String password) {
        System.out.println("findByEmailAndPassword  method in AdminRepo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT a FROM AdminDto a where a.email=:emailId AND a.password=:adminPassword";
            Query query1= entityManager.createQuery(query);
            query1.setParameter("emailId",email);
            query1.setParameter("adminPassword",password);

            AdminDto data= (AdminDto) query1.getSingleResult();
            System.out.println("Data: "+data);
            entityTransaction.commit();
            return data;
        }
        catch (NoResultException e)
        {
            System.out.println("No result found");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally {
            entityManager.close();
            System.out.println("Admin connection closed");
        }

        return null;
    }

    //Admin can view all user data
    @Override
    public List<SignUpDto> findById(SignUpDto dto) {
        System.out.println("findById method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT sys FROM  SignUpDto sys";
            Query query1 = entityManager.createQuery(query);
            List<SignUpDto> data = query1.getResultList();
            System.out.println("Data:"+data);

            return data;
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();
    }

    //Admin to view raised complaint
    @Override
    public List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto) {
        System.out.println("getByComplaintId method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT r FROM  RaiseComplaintDto r";
            Query query1 = entityManager.createQuery(query);
            List<RaiseComplaintDto> datas = query1.getResultList();
            System.out.println("Data:"+datas);

            return datas;
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();
    }

    //admin can view TypeAndCity
    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintType method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT c FROM RaiseComplaintDto c WHERE c.city=:city   And c.complaintType = :complaintType  ";
//            String query = "SELECT r FROM RaiseComplaintDto r where r.city=:city OR r.complaintType=:complaintTypes";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("complaintType", complaintType);
            query1.setParameter("city", city);
            List<RaiseComplaintDto> list = query1.getResultList();
            System.out.println("ComplaintTypeAndCityData:Repoimpl " + list);
            entityTransaction.commit();

            return list;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }


    //type OR city
    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT c FROM RaiseComplaintDto c WHERE  c.city =:city OR c.complaintType =:complaintType ";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("complaintType", complaintType);
            query1.setParameter("city", city);
            List<RaiseComplaintDto> list = query1.getResultList();
            System.out.println("ListOfTypeOrCity: " + list);
            entityTransaction.commit();
            return list;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return Collections.emptyList();
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        System.out.println("saveDepartment method in AdminRepoImpl for departmentDto");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(departmentDto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return departmentDto;
        } finally {
            entityManager.close();
        }
        return departmentDto;
    }

    //find all department in viewRaisecomplaint details where admin view details dropdown
    @Override
    public List<DepartmentDto> findAll(String departmentType) {
        System.out.println("findAll  method  is running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try {
            String query = "select d from DepartmentDto d";

            Query query1 = entityManager.createQuery(query);
            List<DepartmentDto> data = query1.getResultList();
            System.out.println("DepartmentName : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
    }

    //update status and department id
    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {
        System.out.println("updateStatusAndDepartmentId method is running in in AdminRepoImpl.. RaiseComplaintRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String updateQuery = "UPDATE RaiseComplaintDto r SET r.departmentDto.id = :departmentId, r.status = :status WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("departmentId", departmentId);
            query.setParameter("status", status);
            query.setParameter("complaintId", complaintId);

            int data = query.executeUpdate();
            System.out.println("data :" + data);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}

