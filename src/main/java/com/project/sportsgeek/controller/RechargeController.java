package com.project.sportsgeek.controller;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.Recharge;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.RechargeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path = "/recharge",produces = MediaType.APPLICATION_JSON_VALUE)
public class RechargeController {
    @Autowired
    RechargeService rechargeService;

    @GetMapping(path = "/recharge", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Recharge.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<Recharge>>> getAllRecharge() {
        Result<List<Recharge>> rechargeList = rechargeService.findAllRecharge();
        return new ResponseEntity<>(rechargeList, HttpStatus.valueOf(rechargeList.getCode()));
    }

    @GetMapping(path = "/recharge/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Recharge.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Recharge>> getRechargeByRechargeId(@PathVariable int id) throws Exception {
        Result<Recharge> rechargeList = rechargeService.findRechargeByRechargeId(id);
        return new ResponseEntity<>(rechargeList, HttpStatus.valueOf(rechargeList.getCode()));
    }

    @GetMapping(path = "/users/{id}/recharge", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Recharge.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<Recharge>>> getRechargeByUserId(@PathVariable int id) throws Exception {
        Result<List<Recharge>> rechargeList = rechargeService.findRechargeByUserId(id);
        return new ResponseEntity<>(rechargeList, HttpStatus.valueOf(rechargeList.getCode()));
    }

    @PostMapping(path = "/recharge", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Recharge.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Recharge>> addRecharge(@RequestBody(required = true) Recharge recharge) throws  Exception {
        Result<Recharge> rechargeResult = rechargeService.addRecharge(recharge);
        return new ResponseEntity(rechargeResult,HttpStatus.valueOf(rechargeResult.getCode()));
    }

    @PutMapping(value = "/recharge/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Recharge.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Recharge>> updateRecharge(@PathVariable int id,@RequestBody(required = true) Recharge recharge) throws Exception {
        Result<Recharge> rechargeResult = rechargeService.updateRecharge(id,recharge);
        return new ResponseEntity(rechargeResult,HttpStatus.valueOf(rechargeResult.getCode()));
    }

    @DeleteMapping(value = "/recharge/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Recharge.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Recharge>> deleteRechargeById(@PathVariable int id) throws Exception {
        Result<Integer> integerResult =  rechargeService.deleteRecharge(id);
        return new ResponseEntity(integerResult,HttpStatus.valueOf(integerResult.getCode()));
    }
}
