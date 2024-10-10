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

import java.io.IOException;
import java.util.List;

@WebServlet("/requestList")
public class RequestListServlet extends HttpServlet {
    private RequestService requestService;

    @Override
    public void init() throws ServletException {
        super.init();
        RequestRepository requestRepository = new RequestRepositoryImpl();
        this.requestService = new RequestServiceImpl(requestRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/dashboard/index.jsp").forward(request, response);
    }


}
