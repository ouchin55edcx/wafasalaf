package com.ouchin.wafasalaf.Repository.Impl;

import com.ouchin.wafasalaf.Repository.StatusRepository;
import com.ouchin.wafasalaf.config.EntityManagerFactorySingleton;
import com.ouchin.wafasalaf.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class StatusRepositoryImpl implements StatusRepository {

    private final EntityManager em;

    public StatusRepositoryImpl() {
        EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();
        this.em = emf.createEntityManager();
    }

    @Override
    public Status save(Status status) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (status.getId() == 0) {
                em.persist(status);
            } else {
                status = em.merge(status);
            }
            transaction.commit();
            return status;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Status> findById(Long id) {
        return Optional.ofNullable(em.find(Status.class, id));
    }

    @Override
    public List<Status> findAll() {
        return em.createQuery("SELECT s FROM Status s", Status.class).getResultList();
    }
}
