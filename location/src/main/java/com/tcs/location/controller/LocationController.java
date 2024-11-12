package com.tcs.location.controller;

import com.tcs.location.model.EmployeeModel;
import com.tcs.location.model.LocationModel;
import com.tcs.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/{checkInId}")
    public ResponseEntity<LocationModel> createRecord(@RequestParam String city, @RequestParam String country, @PathVariable Long checkInId){
        if(city.isEmpty() || country.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            LocationModel locationModel = locationService.registerLocation(city,country,checkInId);
            return ResponseEntity.ok(locationModel);
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<LocationModel>> getRecords(){
        try{
            List<LocationModel> records = locationService.getRecords();
            return ResponseEntity.ok(records);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{location}/city")
    public ResponseEntity<LocationModel> updateCity(@PathVariable Long location, @RequestParam String city){
       try{
          LocationModel locationModel = locationService.updateCity(location,city);
          return ResponseEntity.ok(locationModel);
       }catch (NoSuchElementException e){
           return ResponseEntity.notFound().build();
       }catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
    }

    @PutMapping("/{location}/country")
    public ResponseEntity<LocationModel> updateCountry(@PathVariable Long location, @RequestParam String country){
       try{
           LocationModel locationModel = locationService.updateCountry(location,country);
           return ResponseEntity.ok(locationModel);
       }catch (NoSuchElementException e){
           return ResponseEntity.notFound().build();
       } catch (Exception e){
          return  ResponseEntity.badRequest().build();
       }
    }

    //Get Id
    @GetMapping("/{location}/record")
    public ResponseEntity<List<LocationModel>> getRecord(@PathVariable Long location){
        try{
            List<LocationModel> records = locationService.getRecordId(location);
            return ResponseEntity.ok(records);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    //Delete ID
    @DeleteMapping("/{location}/delete")
    public ResponseEntity<String> deleteRecord(@PathVariable Long location) {
        try{
            boolean isRemove = locationService.deleteRecord(location);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }}
