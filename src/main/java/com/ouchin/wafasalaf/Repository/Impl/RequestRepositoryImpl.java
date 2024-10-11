package com.ouchin.wafasalaf.Repository.Impl;

import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.config.EntityManagerFactorySingleton;
import com.ouchin.wafasalaf.entity.Historic;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

public class RequestRepositoryImpl implements RequestRepository {

    private final EntityManager em;

    public RequestRepositoryImpl() {
        EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();
        this.em = emf.createEntityManager();
    }

    @Override
    public Request save(Request request) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (request.getId() == null) {
                em.persist(request);
            } else {
                request = em.merge(request);
            }
            transaction.commit();
            return request;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Request> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        return em.createQuery("SELECT r FROM Request r", Request.class).getResultList();
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

    @Override
    public Request findByEmail(String email) {
        try {
            return em.createQuery("SELECT r FROM Request r WHERE r.email = :email", Request.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public void updateStatus(Long requestId, Status newStatus, String description) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Request request = em.find(Request.class, requestId);
            if (request != null) {
                request.addHistoric(newStatus, description);
                em.merge(request);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Historic> getHistoricForRequest(Long requestId) {
        return em.createQuery("SELECT h FROM Historic h WHERE h.request.id = :requestId ORDER BY h.date DESC", Historic.class)
                .setParameter("requestId", requestId)
                .getResultList();
    }
}
