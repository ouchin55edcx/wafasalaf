package com.ouchin.wafasalaf.service.Impl;

import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.entity.Historic;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;
import com.ouchin.wafasalaf.service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {


    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void updateRequestStatus(Long requestId, Status newStatus, String description) {
        requestRepository.updateStatus(requestId, newStatus, description);
    }

    @Override
    public List<Historic> getRequestHistoric(Long requestId) {
        return requestRepository.getHistoricForRequest(requestId);
    }




}
