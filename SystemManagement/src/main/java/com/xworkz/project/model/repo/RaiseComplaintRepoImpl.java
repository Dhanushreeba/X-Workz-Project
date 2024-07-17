package com.xworkz.project.model.repo;

import com.xworkz.project.dto.RaiseComplaintDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;


@Repository
@Slf4j
public class RaiseComplaintRepoImpl implements RaiseComplaintRepo {

    private static final Logger log = LoggerFactory.getLogger(RaiseComplaintRepoImpl.class);
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    RaiseComplaintRepoImpl(){
        System.out.println("no param constr for RaiseComplaintRepoImpl ");
    }

    @Override
    public boolean saveComplaintRaiseData(RaiseComplaintDto raiseComplaintDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(raiseComplaintDto);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            log.error("data not saved for saveComplaintRaiseData...");
        } finally {
            entityManager.close();
            RaiseComplaintRepoImpl.log.info("Connection closed");
        }
        return true;
    }


//    @Override
//    public RaiseComplaintDto userId(RaiseComplaintDto raiseComplaintDto) {
//        return null;
//    }
}
