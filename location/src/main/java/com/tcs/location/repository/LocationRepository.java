package com.tcs.location.repository;

import com.tcs.location.model.LocationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM LocationModel e WHERE e.location = :location")
    void deleteByLocationId(Long location);
}
