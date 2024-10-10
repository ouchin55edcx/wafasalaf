package com.ouchin.wafasalaf;

import com.ouchin.wafasalaf.config.EntityManagerFactorySingleton;
import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.service.RequestService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainDataInsertion {

    public static void main(String[] args) {
        EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Get the request ID (replace this with the actual ID of the saved request)
            Long existingRequestId = 1102L; // Use the actual ID of the request already saved

            // Retrieve the existing request by ID
            Request existingRequest = em.find(Request.class, existingRequestId);

            if (existingRequest != null) {
                // Create and persist a new Status
                Status newStatus = new Status();
                newStatus.setStatus("In Progress"); // Set the appropriate status
                em.persist(newStatus);

                // Add the new historic entry to the existing request
                existingRequest.addHistoric(newStatus, "Status updated to In Progress");

                // Save changes to the request (including the historic entry)
                em.merge(existingRequest); // Use merge to update an existing entity

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
