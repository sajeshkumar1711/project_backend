package com.project.sportsgeek.controller;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.BetOnTeam;
import com.project.sportsgeek.model.BetOnTeamWithResult;
import com.project.sportsgeek.model.BetOnTeamWithUser;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.BetOnTeamService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contest",produces = MediaType.APPLICATION_JSON_VALUE)
public class BetOnTeamController {
    @Autowired
    BetOnTeamService betonteamservice;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = BetOnTeam.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnTeam>> addContest(@RequestBody(required = true) BetOnTeam betonteam) throws  Exception {
        Result<BetOnTeam> betonResult = betonteamservice.addContest(betonteam);
        return new ResponseEntity(betonResult, HttpStatus.valueOf(betonResult.getCode()));
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnTeam.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<BetOnTeamWithUser>>> getContestByMatchId(@PathVariable int id) throws Exception {
//		 	System.out.println("Controller : " + id);
        Result<List<BetOnTeamWithUser>> contestList = betonteamservice.findContestById(id);
        return new ResponseEntity<>(contestList, HttpStatus.valueOf(contestList.getCode()));
    }

    @GetMapping(value = "/contestResult/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnTeam.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<BetOnTeamWithResult>>> getContestResultByMatchId(@PathVariable int id) throws Exception {
//		 	System.out.println("Controller : " + id);
        Result<List<BetOnTeamWithResult>> contestList = betonteamservice.findContestResultByMatchId(id);
        return new ResponseEntity<>(contestList, HttpStatus.valueOf(contestList.getCode()));
    }

    @GetMapping(value = "/{userId}/{matchId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnTeam.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnTeam>> getContestByUserAndMatch(@PathVariable int userId , @PathVariable int matchId) throws Exception {
        Result<BetOnTeam> contestList = betonteamservice.findContestByUserAndMatch(userId, matchId);
        return new ResponseEntity<>(contestList, HttpStatus.valueOf(contestList.getCode()));
    }

    @PutMapping(value = "/update/{betTeamId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = BetOnTeam.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnTeam>> updateContest(@PathVariable int betTeamId,@RequestBody(required = true) BetOnTeam betOnTeam) throws Exception {
        Result<BetOnTeam> betOnTeamResult = betonteamservice.updateContest(betTeamId,betOnTeam);
        return new ResponseEntity(betOnTeamResult,HttpStatus.valueOf(betOnTeamResult.getCode()));
    }
}