package com.tcs.location.repository;

import com.tcs.location.model.CheckInModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckInModel, Long> {
}
