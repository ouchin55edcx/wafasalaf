package com.ouchin.wafasalaf.Repository;

import com.ouchin.wafasalaf.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository {

    Status save(Status status);
    Optional<Status> findById(Long id);
    List<Status> findAll();
}
