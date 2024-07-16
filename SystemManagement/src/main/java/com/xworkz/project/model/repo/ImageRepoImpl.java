package com.xworkz.project.model.repo;

import com.xworkz.project.constant.Status;
import com.xworkz.project.dto.ImageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepoImpl implements ImageRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger log = LoggerFactory.getLogger(ImageRepoImpl.class);

    public ImageRepoImpl() {
        log.info("created constr for ImageRepoImpl");
    }

    @Override
    public boolean saveImage(ImageDto imageDto) {
        log.info("saveImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(imageDto);
            entityTransaction.commit();
            return true; // Return true on successful commit
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error saving image: {}", e.getMessage());
//            if (entityTransaction != null && entityTransaction.isActive()) {
            entityTransaction.rollback();
//            }
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }

        return false; // Return false on failure
    }


    //foreign key set
    @Override
    public Optional<ImageDto> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            String query = "SELECT p FROM ImageDto p WHERE p.user_id = :id";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            return query1.getResultList().stream().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed after the operation
        }
    }


    @Override
    public void imageUpdateDetails(ImageDto imageDto) {

        log.info("updateImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(imageDto);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error updating image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }


    }

    @Override
    public void SetAllImagesInactiveForUser(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "UPDATE ImageDto i SET i.status = :status WHERE i.userId  = :userId";
            Query updateQuery = entityManager.createQuery(query);
            updateQuery.setParameter("status", Status.INACTIVE);
            updateQuery.setParameter("userId", userId);
            int updatedCount = updateQuery.executeUpdate();

            log.info("Number of images set to INACTIVE: {}", updatedCount);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error setting images to INACTIVE for user with ID {}: {}", userId, e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed for SetAllImagesInactiveForUser.");
        }
    }

    @Override
    public void SetAllImagesActiveForUser(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "UPDATE ImageDto i SET i.status = :status WHERE i.userId  = :userId";
            Query updateQuery = entityManager.createQuery(query);
            updateQuery.setParameter("status", Status.ACTIVE);
            updateQuery.setParameter("userId", userId);
            int updatedCount = updateQuery.executeUpdate();

            log.info("Number of images set to ACTIVE: {}", updatedCount);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error setting images to ACTIVE for user with ID {}: {}", userId, e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed for SetAllImagesActiveForUser.");
        }
    }

    @Override
    public List<ImageDto> findByexsitsUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            String query = "SELECT i FROM ImageDto i WHERE i.userId = :userId";
            Query selectQuery = entityManager.createQuery(query);
            selectQuery.setParameter("userId", userId);
            return selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }
}

