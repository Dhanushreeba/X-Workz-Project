package com.xworkz.project.model.repo;

import com.xworkz.project.dto.*;
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

    //register department admin data save
    @Override
    public boolean saveDepartmentAdminData(DepartmentAdminDto departmentAdminDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();
            entityManager.persist(departmentAdminDto);
            entityTransaction.commit();

            return true;
        } catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        } finally
        {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return false;
    }

    //subAdmin login id email exists in database
    @Override
    public DepartmentAdminDto findEmailAndPassword(String email, String password) {
        System.out.println("findEmailAndPassword method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT d FROM DepartmentAdminDto d WHERE d.email=:email AND d.password=:password";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", password);
            DepartmentAdminDto departmentAdminDto = (DepartmentAdminDto) query1.getSingleResult();
            System.out.println("departmentAdminDto :" +departmentAdminDto);
            entityTransaction.commit();
            return departmentAdminDto;

        } catch (NoResultException e) {
            e.printStackTrace();
            System.out.println("No entity found for query findEmailAndPassword");
            return null;  // Or handle the case where no result is found
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return null;
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
    }

    @Override
    public DepartmentAdminDto findByEmail(String email) {
        System.out.println("findByEmail method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            System.out.println(" Existing Email : " + email);
            String query = "SELECT e FROM DepartmentAdminDto e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            DepartmentAdminDto data = (DepartmentAdminDto) query1.getSingleResult();
            System.out.println("email :" + data);
            entityTransaction.commit();
            return data;

        } catch (NoResultException e) {
            System.out.println("No entity found for query in findByEmail");
            entityTransaction.rollback(); // Rollback the transaction in case of no result
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback(); // Rollback the transaction in case of other persistence exceptions
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
    }

    @Override
    public DepartmentAdminDto resetPasswordEmail(String email) {
        System.out.println("resetPasswordEmail method running in AdminRepoImpl..{}" +email);
        System.out.println("resetPasswordEmail : " +email);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT f FROM DepartmentAdminDto f WHERE f.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            DepartmentAdminDto findEmail = (DepartmentAdminDto) query1.getSingleResult();

            System.out.println("FindEmail : {}" +findEmail);

            return findEmail;
        } catch (PersistenceException persistenceException) {
            System.out.println("No entity found for query in resetPasswordEmail");
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
    }

    @Override
    public void updatePassword(String email, String password) {
        System.out.println("updatePassword method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "UPDATE DepartmentAdminDto u SET u.password=:newPassword WHERE u.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("newPassword", password);
            query1.setParameter("email", email);

            int update = query1.executeUpdate();
            System.out.println("forgotPasswordUpdate :{}" +update);
            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("No entity found for query in updatePassword");
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
    }

    @Override
    public boolean update(DepartmentAdminDto departmentAdminDto) {
        System.out.println("update method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(departmentAdminDto);
            entityTransaction.commit();
        }
        catch (PersistenceException persistenceException)
        {
            System.out.println("No found for  update");
            persistenceException.printStackTrace();
            entityManager.close();
        }
        finally {
            entityManager.close();
        }


        return true;
    }

    @Override
    public boolean emailExists(String email) {
        //check if email exists in database or not
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT d FROM DepartmentAdminDto d where d.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            DepartmentAdminDto departmentAdminDto = (DepartmentAdminDto) query1.getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean verifyOldPassword(String email, String oldPassword) {
        //to verify  the old password

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try {
            String query = "SELECT d FROM DepartmentAdminDto d WHERE d.email=:email AND d.password=:password ";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", oldPassword);
            DepartmentAdminDto departmentAdminDto = (DepartmentAdminDto) query1.getSingleResult();
            System.out.println(departmentAdminDto);
            return true;
        } catch (NoResultException e) {
            // If no result is found, return false
            return false;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void updatedPassword(String email, String newPassword) {
        // to update the reset password to password in database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("email :"+email + "password :"+newPassword);


        try {

            entityTransaction.begin();
            //update table name set password=? where email=?;

            String query = "UPDATE dep DepartmentAdminDto dep SET dep.password=:password WHERE dep.email=:email ";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("password", newPassword);
            query1.setParameter("email", email);

            int executeData = query1.executeUpdate();
            System.out.println(executeData);
            entityTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {

            entityManager.close();
        }
    }

}

