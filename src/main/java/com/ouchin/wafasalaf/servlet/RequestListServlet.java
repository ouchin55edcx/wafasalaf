package com.ouchin.wafasalaf.servlet;

import com.ouchin.wafasalaf.Repository.Impl.StatusRepositoryImpl;
import com.ouchin.wafasalaf.Repository.Impl.StatusServiceImpl;
import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.Repository.StatusRepository;
import com.ouchin.wafasalaf.entity.Status;
import com.ouchin.wafasalaf.service.RequestService;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.service.StatusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/requestList")
public class RequestListServlet extends HttpServlet {
    private RequestService requestService;
    private StatusService statusService;

    @Override
    public void init() throws ServletException {
        super.init();
        RequestRepository requestRepository = new RequestRepositoryImpl();
        this.requestService = new RequestServiceImpl(requestRepository);
        StatusRepository statusRepository = new StatusRepositoryImpl();
        this.statusService = new StatusServiceImpl(statusRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statusId = request.getParameter("statusId");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        Long statusIdLong = null;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (statusId != null && !statusId.isEmpty()) {
            statusIdLong = Long.parseLong(statusId);
        }
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = LocalDate.parse(startDateStr);
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = LocalDate.parse(endDateStr);
        }

        List<Request> requests = requestService.getFilteredRequests(statusIdLong, startDate, endDate);
        List<Status> allStatuses = statusService.getAllStatuses();

        request.setAttribute("requests", requests);
        request.setAttribute("allStatuses", allStatuses);
        request.setAttribute("selectedStatusId", statusIdLong);
        request.setAttribute("startDate", startDateStr);
        request.setAttribute("endDate", endDateStr);

        request.getRequestDispatcher("/dashboard/index.jsp").forward(request, response);
    }
}


