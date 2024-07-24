package com.xworkz.project.model.repo;

import com.xworkz.project.dto.AdminDto;
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

    //Search by city
    @Override
    public List<RaiseComplaintDto> searchByCity(String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM RaiseComplaintDto c WHERE c.city = :city");
            query.setParameter("city", city);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    //search by type and city
    @Override
    public List<RaiseComplaintDto> searchByTypeAndCity(String complaintType, String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String queryString = "SELECT c FROM RaiseComplaintDto c WHERE 1=1";
            if (complaintType != null && !complaintType.isEmpty()) {
                queryString += " AND c.complaintType = :complaintType";
            }
            if (city != null && !city.isEmpty()) {
                queryString += " AND c.city = :city";
            }
            Query query = entityManager.createQuery(queryString);
            if (complaintType != null && !complaintType.isEmpty()) {
                query.setParameter("complaintType", complaintType);
            }
            if (city != null && !city.isEmpty()) {
                query.setParameter("city", city);
            }
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<RaiseComplaintDto> searchByType(String complaintType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM RaiseComplaintDto c WHERE c.complaintType = :complaintType");
            query.setParameter("complaintType", complaintType);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

}

//search operation to search by city
//    @Override
//    public List<RaiseComplaintDto> searchByCity(RaiseComplaintDto raiseComplaintDto) {
//        System.out.println("Running searchByCity method in AdminRepoImpl ");
//        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//
//
//        try {
//            String query = "select r from RaiseComplaintDto r where r.city=:city";
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("city", raiseComplaintDto.getCity());
//            List<RaiseComplaintDto> resultList = query1.getResultList();
//
//            entityTransaction.commit();
//            return resultList;
//
//        } catch (PersistenceException persistenceException) {
//            persistenceException.printStackTrace();
//            entityTransaction.rollback();
//        } finally {
//            entityManager.close();
//        }
//
//        return Collections.emptyList();
//    }
