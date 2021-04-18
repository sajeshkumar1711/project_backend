package com.project.sportsgeek.controller;

import com.project.sportsgeek.model.Recharge;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recharge",produces = MediaType.APPLICATION_JSON_VALUE)
public class RechargeController {
    @Autowired
    RechargeService rechargeService;

    @GetMapping
    public ResponseEntity<Result<List<Recharge>>> getAllRecharge() {
        Result<List<Recharge>> rechargeList = rechargeService.findAllRecharge();
        return new ResponseEntity<>(rechargeList, HttpStatus.valueOf(rechargeList.getCode()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<Recharge>> getRechargeByUserId(@PathVariable int id) throws Exception {
        Result<Recharge> rechargeList = rechargeService.findRechargeByUserId(id);
        return new ResponseEntity<>(rechargeList, HttpStatus.valueOf(rechargeList.getCode()));
    }
    @PostMapping
    public ResponseEntity<Result<Recharge>> addRecharge(@RequestBody(required = true) Recharge recharge) throws  Exception {
        Result<Recharge> rechargeResult = rechargeService.addRecharge(recharge);
        return new ResponseEntity(rechargeResult,HttpStatus.valueOf(rechargeResult.getCode()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result<Recharge>> updateRecharge(@PathVariable int id,@RequestBody(required = true) Recharge recharge) throws Exception {
        Result<Recharge> rechargeResult = rechargeService.updateRecharge(id,recharge);
        return new ResponseEntity(rechargeResult,HttpStatus.valueOf(rechargeResult.getCode()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Recharge>> deleteRechargeById(@PathVariable int id) throws Exception {
        Result<Integer> integerResult =  rechargeService.deleteRecharge(id);
        return new ResponseEntity(integerResult,HttpStatus.valueOf(integerResult.getCode()));
    }
}
