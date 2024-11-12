package com.tcs.location.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "check_in")
public class CheckInModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkInId;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    @Column(name = "status")
    private Boolean status = false;

//    @Column(name = "employee_id")
//    private EmployeeModel employee;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference("employee-check")
    private EmployeeModel employee;

    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Cascade to manage check-ins/ fetch =
    @JsonBackReference("check-location")
    private List<LocationModel> locationModels= new ArrayList<>();


//    public EmployeeModel getEmployee(){
//        return  employee;
//    }
//
//    public void setEmployee(EmployeeModel employee){
//        this.employee = employee;
//    }
//
}
