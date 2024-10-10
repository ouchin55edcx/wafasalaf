package com.ouchin.wafasalaf.Repository.Impl;

import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.config.EntityManagerFactorySingleton;
import com.ouchin.wafasalaf.entity.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class RequestRepositoryImpl implements RequestRepository {
    @Override
    public Request save(Request request) {
        EntityManager em = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
        return request;
    }

    @Override
    public Optional<Request> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        return List.of();
    }

    @Override
    public void update(Request request) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Request> findByStatus() {
        return List.of();
    }

    @Override
    public List<Request> findByDate() {
        return List.of();
    }
}
