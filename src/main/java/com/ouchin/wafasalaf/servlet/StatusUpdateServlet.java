package com.ouchin.wafasalaf.servlet;

import com.ouchin.wafasalaf.Repository.Impl.StatusRepositoryImpl;
import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.Repository.Impl.StatusServiceImpl;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.entity.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateStatus")
public class StatusUpdateServlet extends HttpServlet {
    private StatusServiceImpl statusService;
    private RequestServiceImpl requestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.statusService = new StatusServiceImpl(new StatusRepositoryImpl());
        this.requestService = new RequestServiceImpl(new RequestRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("id"));
        request.setAttribute("requestId", requestId);
        request.setAttribute("statuses", statusService.getAllStatuses());
        request.getRequestDispatcher("/dashboard/updateStatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("requestId"));
        Long statusId = Long.parseLong(request.getParameter("statusId"));
        String description = request.getParameter("description");

        Status newStatus = statusService.getStatusById(statusId).orElseThrow();
        requestService.updateRequestStatus(requestId, newStatus, description);

        response.sendRedirect(request.getContextPath() + "/requestList");
    }
}