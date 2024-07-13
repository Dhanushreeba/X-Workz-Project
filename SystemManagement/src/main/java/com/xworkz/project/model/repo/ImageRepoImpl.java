package com.xworkz.project.model.repo;

import com.xworkz.project.dto.ImageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class ImageRepoImpl implements ImageRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger log = LoggerFactory.getLogger(ImageRepoImpl.class);

    ImageRepoImpl(){
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
        } catch (Exception e)
        {
            log.error("Error saving image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive())
            {
                entityTransaction.rollback();
            }
            return false; // Return false on failure
        }
        finally
        {
            entityManager.close();
            log.info("Connection closed");
        }
    }

    @Override
    public Optional<ImageDto> findByUserId(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction =  entityManager.getTransaction();

        try {
            String query = "SELECT p FROM EditProfileImageDTO p WHERE p.imageUserId = :id";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            return query1.getResultList().stream().findFirst();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally
        {
            entityManager.close(); // Ensure the EntityManager is closed after the operation
        }
    }
}
