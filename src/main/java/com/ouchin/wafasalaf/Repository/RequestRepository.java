package com.ouchin.wafasalaf.Repository;

import com.ouchin.wafasalaf.entity.Historic;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;

import java.util.List;
import java.util.Optional;

public interface RequestRepository {

    Request save(Request request);
    Optional<Request> findById(Long id);
    List<Request> findAll();
    void update(Request request);
    void delete(Long id);
    List<Request> findByStatus();
    List<Request> findByDate();
    Request findByEmail(String email);
    void updateStatus(Long requestId, Status newStatus, String description);
    List<Historic> getHistoricForRequest(Long requestId);

}
