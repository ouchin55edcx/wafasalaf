package com.ouchin.wafasalaf;

import com.ouchin.wafasalaf.config.EntityManagerFactorySingleton;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainDataInsertion {

    public static void main(String[] args) {
        EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Long existingRequestId = 1102L;

            Request existingRequest = em.find(Request.class, existingRequestId);

            if (existingRequest != null) {
                Status newStatus = new Status();
                newStatus.setStatus("In Progress");
                em.persist(newStatus);

                existingRequest.addHistoric(newStatus, "Status updated to In Progress");

                em.merge(existingRequest);
                System.out.println("Status and historic entry added to the request with ID: " + existingRequestId);
            } else {
                System.out.println("Request with ID " + existingRequestId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            EntityManagerFactorySingleton.closeEntityManagerFactory();
        }
    }
}
