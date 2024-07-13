package com.xworkz.project.model.repo;

import com.xworkz.project.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Slf4j
@Repository
public class EditUserRepoImpl implements EditUserRepo {

    private static final Logger log = LoggerFactory.getLogger(EditUserRepoImpl.class);
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EditUserRepoImpl() {
        EditUserRepoImpl.log.info("created constr for EditUserRepoImpl");
    }

    //Edit
    @Override
    public SignUpDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "select sys from SignUpDto sys where sys.email= :email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            return (SignUpDto) query1.getSingleResult();
        } catch (NoResultException noResultException) {
            noResultException.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    //Edit
    @Override
    public SignUpDto editByEmail(SignUpDto updatedUserDetails) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            // Retrieve the existing user
            SignUpDto existingUser = findByEmail(updatedUserDetails.getEmail());
            if (existingUser != null) {
                // Update the user details
                existingUser.setFirstName(updatedUserDetails.getFirstName());
                existingUser.setLastName(updatedUserDetails.getLastName());
                existingUser.setContactNumber(updatedUserDetails.getContactNumber());
                existingUser.setAlternateNumber(updatedUserDetails.getAlternateNumber());
                existingUser.setAddress(updatedUserDetails.getAddress());
                existingUser.setAgree(updatedUserDetails.getAgree());

                // Persist the changes
                entityManager.merge(existingUser);
                entityTransaction.commit();
                return existingUser;
            }
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
        return null;
    }


    @Override
    public SignUpDto update(SignUpDto dto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(dto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();

        } finally {
            entityManager.close();
        }

        return dto;
    }
}