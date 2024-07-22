package com.xworkz.project.model.repo;

import com.xworkz.project.dto.RaiseComplaintDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


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

    //after setting foriegnKey
    @Override
    public Optional<RaiseComplaintDto> findByUserId(int id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDto> query = entityManager.createQuery(
                    "SELECT i FROM RaiseComplaintDto i WHERE i.signup_id = :id", RaiseComplaintDto.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }

    }

    //view complaints
    @Override
    public List<RaiseComplaintDto> findByRaise(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDto> query = entityManager.createQuery(
                    "SELECT r FROM RaiseComplaintDto r WHERE r.dto.id= :userId", RaiseComplaintDto.class);
            query.setParameter("userId", id);
            List<RaiseComplaintDto> results = query.getResultList();
            System.out.println("results---------"+results);
            log.info("Found {} complaints for user ID {}", results.size(), id);
            return results;
        } finally {
            entityManager.close();
        }
    }

}
