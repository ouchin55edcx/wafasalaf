package com.ouchin.wafasalaf.service;

import com.ouchin.wafasalaf.entity.Request;

import java.util.List;

public interface RequestService {
    Request createRequest(Request request);
    List<Request> getAllRequests();


}
