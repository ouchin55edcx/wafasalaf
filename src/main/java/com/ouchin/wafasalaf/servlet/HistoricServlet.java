package com.ouchin.wafasalaf.servlet;

import com.ouchin.wafasalaf.Repository.Impl.RequestRepositoryImpl;
import com.ouchin.wafasalaf.service.Impl.RequestServiceImpl;
import com.ouchin.wafasalaf.entity.Historic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewHistoric")
public class HistoricServlet extends HttpServlet {
    private RequestServiceImpl requestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.requestService = new RequestServiceImpl(new RequestRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("id"));
        List<Historic> historics = requestService.getRequestHistoric(requestId);
        request.setAttribute("historics", historics);
        request.getRequestDispatcher("/dashboard/historic.jsp").forward(request, response);
    }
}