package com.ouchin.wafasalaf.servlet;

import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.service.RequestService;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/submitRequest")
public class RequestServlet extends HttpServlet {
    private RequestService requestService;
    private Validator validator;

    @Override
    public void init() throws ServletException {
        super.init();
        RequestRepository requestRepository = new RequestRepositoryImpl();
        this.requestService = new RequestServiceImpl(requestRepository);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request newRequest = createRequestFromParameters(request);

        // Validation
        Set<ConstraintViolation<Request>> violations = validator.validate(newRequest);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Validation failed: " + errorMessage);
            return;
        }

        Request savedRequest = requestService.createRequest(newRequest);

        if (savedRequest != null && savedRequest.getId() != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Your request was successfully submitted!");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to save the request.");
        }
    }

    private Request createRequestFromParameters(HttpServletRequest request) throws ServletException {
        Request newRequest = new Request();
        request.getParameterMap().forEach((k, v) -> System.out.println("key => " + k + " value => " + Arrays.asList(v)));

        // Parse and set request parameters
        newRequest.setProject_name(request.getParameter("project"));
        newRequest.setProfession(request.getParameter("profession"));
        newRequest.setAmount(Integer.parseInt(request.getParameter("amount")));
        newRequest.setDuration(Integer.parseInt(request.getParameter("duration")));
        newRequest.setMonthly_payments(Float.parseFloat(request.getParameter("monthly")));
        newRequest.setEmail(request.getParameter("email"));
        newRequest.setPhone_number(request.getParameter("phone"));
        newRequest.setCivility(request.getParameter("civilite"));
        newRequest.setFirst_name(request.getParameter("prenom"));
        newRequest.setLast_name(request.getParameter("nom"));
        newRequest.setCin(request.getParameter("CIN"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd").withLocale(Locale.FRANCE);
        String dateOfBirth = request.getParameter("datenaissance");
        newRequest.setDate_of_birth(LocalDate.parse(dateOfBirth, formatter));

        String startDate = request.getParameter("datedembauche");
        newRequest.setStart_date(LocalDate.parse(startDate, formatter));

        newRequest.setMonthly_net_income(new BigDecimal(request.getParameter("totalrevenue")));
        newRequest.setHas_current_loans(request.getParameter("credits").equals("Oui"));

        // Handle optional fields
        String creditImmo = request.getParameter("creditImmo");
        newRequest.setMortgage_monthly_payment(creditImmo != null && !creditImmo.isEmpty() ? new BigDecimal(creditImmo) : BigDecimal.ZERO);

        String otherCredits = request.getParameter("otherCredits");
        newRequest.setOther_loans_monthly_payment(otherCredits != null && !otherCredits.isEmpty() ? new BigDecimal(otherCredits) : BigDecimal.ZERO);

        return newRequest;
    }
}
