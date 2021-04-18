package com.project.sportsgeek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sportsgeek.model.Gender;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.GenderService;

@RestController
@RequestMapping(path = "/gender",produces = MediaType.APPLICATION_JSON_VALUE)
public class GenderController {

	@Autowired
    GenderService genderService;

    @GetMapping
    public ResponseEntity<Result<List<Gender>>> getAllGender() {
        Result<List<Gender>> genderList = genderService.findAllGender();
        return new ResponseEntity<>(genderList, HttpStatus.valueOf(genderList.getCode()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<Gender>> getGenderById(@PathVariable int id) throws Exception {
        Result<Gender> genderList = genderService.findGenderById(id);
        return new ResponseEntity<>(genderList, HttpStatus.valueOf(genderList.getCode()));
    }

    @PostMapping
    public ResponseEntity<Result<Gender>> addGender(@RequestBody(required = true) Gender gender) throws  Exception {
        Result<Gender> genderResult = genderService.addGender(gender);
        return new ResponseEntity(genderResult,HttpStatus.valueOf(genderResult.getCode()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result<Gender>> updateGender(@PathVariable int id,@RequestBody(required = true) Gender gender) throws Exception {
       Result<Gender> genderResult = genderService.updateGender(id,gender);
        return new ResponseEntity(genderResult,HttpStatus.valueOf(genderResult.getCode()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Gender>> deleteGenderById(@PathVariable int id) throws Exception {
       Result<Integer> integerResult =  genderService.deleteGender(id);
        return new ResponseEntity(integerResult,HttpStatus.valueOf(integerResult.getCode()));
    }
}
