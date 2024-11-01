package com.tcs.location.service;

import com.tcs.location.model.CheckInModel;
import com.tcs.location.model.EmployeeModel;
import com.tcs.location.model.LocationModel;
import com.tcs.location.repository.CheckInRepository;
import com.tcs.location.repository.EmployeeRepository;
import com.tcs.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private LocationRepository locationRepository;

    //delete
    public void deleteRecord(Long location){
        locationRepository.deleteById(location);
    }

    //update city
    public LocationModel updateCity(Long location, String city){
       LocationModel locationModel = locationRepository.findById(location).orElseThrow(NoSuchElementException::new);
       locationModel.setCity(city);
       return locationRepository.save(locationModel);
    }

    //update country
    public LocationModel updateCountry(Long location, String country){
        LocationModel locationModel = locationRepository.findById(location).orElseThrow(NoSuchElementException::new);
        locationModel.setCountry(country);
        return locationRepository.save(locationModel);
    }

    //post //Long id, Long checkInId
    public LocationModel registerLocation(String city, String country, Long checkInId, Long id){
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        CheckInModel checkInModel = checkInRepository.findById(checkInId).orElseThrow(NoSuchElementException::new);

       LocationModel locationModel = new LocationModel();
       locationModel.setCity(city);
       locationModel.setCountry(country);
       locationModel.setCheckInModel(checkInModel);
       locationModel.setEmployee(employeeModel);
       return locationRepository.save(locationModel);
    }

    //get
    public List<LocationModel> getRecords(){
        List<LocationModel> records = new ArrayList<>();
        for(LocationModel locationModel: locationRepository.findAll()){
            records.add(locationModel);
        }
        return records;
    }

    //GetById
    public List<LocationModel> getRecordId(Long location){
        LocationModel locationModel = locationRepository.findById(location).orElseThrow(NoSuchElementException::new);
        List<LocationModel> records = new LinkedList<>();
        records.add(locationModel);
        return records;
    }
    //DeleteById

}
