package com.tcs.location.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "location")
public class LocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  locationId;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private EmployeeModel employee;
}
