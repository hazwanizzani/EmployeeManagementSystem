package com.project.manager.repository;

import com.project.manager.model.LeaveApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationRepository extends CrudRepository<LeaveApplication, Long> {

    List<LeaveApplication> findByEmployeeId(Long id);
}
