package com.ouchin.wafasalaf.service.Impl;

import com.ouchin.wafasalaf.Repository.RequestRepository;
import com.ouchin.wafasalaf.entity.Request;
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

}
