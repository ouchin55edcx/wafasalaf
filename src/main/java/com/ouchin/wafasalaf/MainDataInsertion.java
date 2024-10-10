package com.ouchin.wafasalaf;



import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.service.RequestService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainDataInsertion {

    public static void main(String[] args) {
        // Create repository and service instances
        RequestRepository requestRepository = new RequestRepositoryImpl();
        RequestService requestService = new RequestServiceImpl(requestRepository);

        // Create a fake Request object
        Request fakeRequest = new Request();
        fakeRequest.setProject_name("Test Project");
        fakeRequest.setProfession("Software Developer");
        fakeRequest.setAmount(50000);
        fakeRequest.setMonthly_payments(1000.0f);
        fakeRequest.setEmail("test@example.com");
        fakeRequest.setPhone_number("1234567890");
        fakeRequest.setCivility("Mr.");
        fakeRequest.setFirst_name("John");
        fakeRequest.setLast_name("Doe");
        fakeRequest.setCin("AB123456");
        fakeRequest.setDate_of_birth(LocalDate.of(1990, 1, 1));
        fakeRequest.setStart_date(LocalDate.now());
        fakeRequest.setMonthly_net_income(new BigDecimal("5000.00"));
        fakeRequest.setHas_current_loans(false);
        fakeRequest.setDuration(12);


        // Save the fake request
        Request savedRequest = requestService.createRequest(fakeRequest);

        if (savedRequest != null && savedRequest.getId() != null) {
            System.out.println("Request saved successfully with ID: " + savedRequest.getId());
        } else {
            System.out.println("Failed to save the request.");
        }
    }
}