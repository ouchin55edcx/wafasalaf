package com.ouchin.wafasalaf.Repository;

import com.ouchin.wafasalaf.entity.Historic;
import com.ouchin.wafasalaf.entity.Request;
import com.ouchin.wafasalaf.entity.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RequestRepository {

    Request save(Request request);
    Optional<Request> findById(Long id);
    List<Request> findAll();
    void update(Request request);
    void delete(Long id);
    void updateStatus(Long requestId, Status newStatus, String description);
    List<Historic> getHistoricForRequest(Long requestId);

    List<Request> findByStatusAndDate(Long statusId, LocalDate startDate, LocalDate endDate);
    List<Request> findByStatus(Long statusId);
    List<Request> findByDateRange(LocalDate startDate, LocalDate endDate);

}
