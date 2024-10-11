package com.ouchin.wafasalaf.service.Impl;

import com.ouchin.wafasalaf.entity.Status;
import com.ouchin.wafasalaf.service.StatusService;

import java.util.List;
import java.util.Optional;

public class StatusServiceImpl implements StatusService {
    @Override
    public Status createStatus(Status status) {
        return null;
    }

    @Override
    public Optional<Status> getStatusById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Status> getAllStatuses() {
        return List.of();
    }
}
