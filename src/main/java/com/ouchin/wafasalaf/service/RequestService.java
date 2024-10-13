package com.ouchin.wafasalaf.service;

import com.ouchin.wafasalaf.entity.Historic;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;

import java.time.LocalDate;
import java.util.List;

public interface RequestService {
    Request createRequest(Request request);
    List<Request> getAllRequests();
    void updateRequestStatus(Long requestId, Status newStatus, String description);
    List<Historic> getRequestHistoric(Long requestId);
    List<Request> getFilteredRequests(Long statusId, LocalDate startDate, LocalDate endDate);

}
