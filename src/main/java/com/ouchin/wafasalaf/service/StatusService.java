package com.ouchin.wafasalaf.service;

import com.ouchin.wafasalaf.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    Status createStatus(Status status);
    Optional<Status> getStatusById(Long id);
    List<Status> getAllStatuses();
}


