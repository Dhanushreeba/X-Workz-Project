package com.xworkz.project.model.repo;

import com.xworkz.project.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class SignUpRepoImpl implements SignUpRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public SignUpRepoImpl() {
        System.out.println("running constr for SignUpRepoImpl class");
    }

    //saveAndvalidation for systemManagement
    @Override
    public boolean saveAndValidation(SignUpDto dto) {
        System.out.println("saveAndValidation method in saveAndValidation");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(dto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public SignUpDto findByEmailAndPassword(String email, String password) {
        System.out.println("findByEmailAndPassword method in SignUpRepoImpl");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "Select sys from SignUpDto sys where sys.email=:email AND sys.password=:password";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", password);
            SignUpDto dto = (SignUpDto) query1.getSingleResult();
            System.out.println(dto);
            entityTransaction.commit();
            return dto;
        } catch (Exception exception) {
            exception.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    //login lock
    @Override
    public SignUpDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select sys from SignUpDto sys where sys.email=:email");
            query.setParameter("email", email);

            List<SignUpDto> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                System.out.println("No result found for email :" + email);
                return null;
            } else if (resultList.size() == 1) {
                return resultList.get(0);
            } else {
                throw new NonUniqueResultException("Multiple results found for email: " + email);
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    //lock
    @Override
    public boolean update(SignUpDto dto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(dto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }

//    @Override
//    public SystemDto existsByEmail(String email) {
//
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            try {
//                Query query2 = entityManager.createQuery("select sys from SystemDto sys where sys.email=:email ");
//                query2.setParameter("email", email);
//                System.out.println("Running");
//                SystemDto dto = (SystemDto) query2.getSingleResult();
//                return dto;
//            } catch (PersistenceException persistenceException) {
//                persistenceException.printStackTrace();
//            } finally {
//                entityManager.close();
//            }
//            return null;
//        }

    // if email saved then it should come as email exists
    @Override
    public SignUpDto existsByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query2 = entityManager.createQuery("select sys from SignUpDto sys where sys.email=:email ");
            query2.setParameter("email", email);
            System.out.println("Running existsByEmail method in SignUpRepoImpl");
            return (SignUpDto) query2.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

//    @Override
//    public SignUpDto existsByContactNumber(Long contactNumber) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            Query query = entityManager.createQuery("select sys from SignUpDto sys where sys.contactNumber=:contactNumber ");
//            query.setParameter("contactNumber", contactNumber);
//            System.out.println("Running existsByContactNumber method in SignUpRepoImpl");
//            return (SignUpDto) query.getSingleResult();
//        } catch (NoResultException noResultException) {
//            return null;
//        } catch (PersistenceException persistenceException) {
//            persistenceException.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//        return null;
//    }

    //if contact saved then it should come as contact exists
    @Override
    public SignUpDto existsByContactNumber(Long contactNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select sys from SignUpDto sys where sys.contactNumber=:contactNumber ");
            query.setParameter("contactNumber", contactNumber);
            System.out.println("Running existsByContactNumber method in SignUpRepoImpl");
            return (SignUpDto) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }
//
//    @Override
//    public SignUpDto lookByEmail(String email) {
//        System.out.println("lookByEmail method in SignUpRepoImpl");
//        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction=entityManager.getTransaction();
//
//        try {
//            entityTransaction.begin();
//            String query = "Select sys from SignUpDto sys where sys.email=:email";
//            Query query1=entityManager.createQuery(query);
//            query1.setParameter("email", email);
//            SignUpDto dto=(SignUpDto) query1.getSingleResult();
//            System.out.println(dto);
//            entityTransaction.commit();
//            return dto;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            entityTransaction.rollback();
//        } finally {
//            entityManager.close();
//        }
//        return null;
//    }

    //forget password
    @Override
    public SignUpDto lookByEmail(String email) {
        System.out.println("lookByEmail method in SignUpRepoImpl");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Query query2 = entityManager.createQuery("select s from SignUpDto s where s.email=:email ");
            query2.setParameter("email", email);
            System.out.println("Running lookByEmail method in SignUpRepoImpl");
            entityTransaction.commit();
            return (SignUpDto) query2.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    //ResetPassword
        @Override
        public boolean emailExists (String email){
            //check if email exists in database or not
            EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        String query = "SELECT COUNT(e) FROM SignUpDTO e WHERE e.email=:email";
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("email", email);
//        Long count = (Long) query1.getSingleResult();
//        System.out.println(count);
            try {
                String query = "SELECT sys FROM SignUpDto sys where sys.email=:email";
                Query query1 = entityManager.createQuery(query);
                query1.setParameter("email", email);

                SignUpDto dto = (SignUpDto) query1.getSingleResult();


                return true;
            } catch (NoResultException e) {
                return false;
            } finally {
                entityManager.close();
            }
        }

        //ResetPassword
        @Override
        public boolean verifyOldPassword (String email, String oldPassword){

            //to verify  the old password

            EntityManager entityManager = entityManagerFactory.createEntityManager();

//        String query = "SELECT COUNT(e) FROM SignUpDTO e  WHERE email =:email AND e.password=:password";
//
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("email", email);
//        query1.setParameter("password", oldPassword);
//        Long count = (Long) query1.getSingleResult();
//        System.out.println(count);

            try {
                String query = "SELECT sys FROM SignUpDto sys WHERE sys.email=:email AND sys.password=:password ";
                Query query1 = entityManager.createQuery(query);
                query1.setParameter("email", email);
                query1.setParameter("password", oldPassword);
                SignUpDto dto = (SignUpDto) query1.getSingleResult();
                System.out.println(dto);
                return true;
            } catch (NoResultException e) {
                // If no result is found, return false
                return false;
            } finally {
                entityManager.close();
            }

        }

//        ResetPassword
        @Override
        // @Transactional
        public void updatePassword (String email, String newPassword){
            // to update the reset password to password in database
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();


            try {

                entityTransaction.begin();
                //update table name set password=? where email=?;

                String query = "UPDATE SignUpDto sys SET sys.password=:password WHERE sys.email=:email ";

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

        //view
    @Override
    public SignUpDto getUserByEmail(String email) {
        System.out.println("checkByEmail method in SignUpRepoImpl");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("SELECT s FROM SignUpDto s WHERE s.email = :email", SignUpDto.class);
            query.setParameter("email", email);
            SignUpDto signUpDto = (SignUpDto) query.getSingleResult();
            entityTransaction.commit();
            return signUpDto;
        } catch (NoResultException e) {
            entityTransaction.rollback();
            System.out.println("No result found for email: " + email);
            return null;
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }



}
