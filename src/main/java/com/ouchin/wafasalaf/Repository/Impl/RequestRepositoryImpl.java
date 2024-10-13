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

import java.time.LocalDate;
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

    @Override
    public List<Request> findByStatusAndDate(Long statusId, LocalDate startDate, LocalDate endDate) {
        String jpql = "SELECT DISTINCT r FROM Request r JOIN r.historics h WHERE h.status.id = :statusId AND h.date BETWEEN :startDate AND :endDate ORDER BY r.id";
        return em.createQuery(jpql, Request.class)
                .setParameter("statusId", statusId)
                .setParameter("startDate", startDate.atStartOfDay())
                .setParameter("endDate", endDate.atTime(23, 59, 59))
                .getResultList();
    }

    @Override
    public List<Request> findByStatus(Long statusId) {
        String jpql = "SELECT DISTINCT r FROM Request r JOIN r.historics h WHERE h.status.id = :statusId ORDER BY r.id";
        return em.createQuery(jpql, Request.class)
                .setParameter("statusId", statusId)
                .getResultList();
    }

    @Override
    public List<Request> findByDateRange(LocalDate startDate, LocalDate endDate) {
        String jpql = "SELECT DISTINCT r FROM Request r JOIN r.historics h WHERE h.date BETWEEN :startDate AND :endDate ORDER BY r.id";
        return em.createQuery(jpql, Request.class)
                .setParameter("startDate", startDate.atStartOfDay())
                .setParameter("endDate", endDate.atTime(23, 59, 59))
                .getResultList();
    }


}
