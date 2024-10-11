package com.ouchin.wafasalaf.Repository.Impl;

import com.ouchin.wafasalaf.Repository.StatusRepository;
import com.ouchin.wafasalaf.entity.Status;
import com.ouchin.wafasalaf.service.StatusService;

import java.util.List;
import java.util.Optional;

public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Optional<Status> getStatusById(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}
