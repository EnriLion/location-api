package com.tcs.location.repository;

//import com.tcs.check_in_check_out_system.model.CheckInModel;
//import com.tcs.check_in_check_out_system.model.EmployeeModel;
import com.tcs.location.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
