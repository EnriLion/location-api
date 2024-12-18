package com.tcs.location.service;

import com.tcs.location.model.CheckInModel;
import com.tcs.location.model.EmployeeModel;
import com.tcs.location.model.LocationModel;
import com.tcs.location.repository.CheckInRepository;
import com.tcs.location.repository.EmployeeRepository;
import com.tcs.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LocationService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private LocationRepository locationRepository;


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
    public LocationModel registerLocation(String city, String country, Long checkInId){
        CheckInModel checkInModel = checkInRepository.findById(checkInId).orElseThrow(NoSuchElementException::new);
        LocationModel locationModel = new LocationModel();
        if(checkInModel.getStatus()){
            EmployeeModel employeeModel = checkInModel.getEmployee();
            locationModel.setCity(city);
            locationModel.setCountry(country);
            locationModel.setCheck(checkInModel);
//            locationModel.setEmployee(employeeModel);
            locationModel.setEmployeeId(employeeModel);

            checkInModel.getLocationModels().add(locationModel);
            employeeModel.getLocationModels().add(locationModel);

            return locationRepository.save(locationModel);
        } else {
            throw new NoSuchElementException();
        }
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
    public boolean deleteRecord(Long location) {
        LocationModel locationModel = locationRepository.findById(location).orElseThrow(NoSuchElementException::new);
        if(locationModel != null){
            locationRepository.deleteByLocationId(location);
            return true;
        } else {
            return false;
        }
    }
}
