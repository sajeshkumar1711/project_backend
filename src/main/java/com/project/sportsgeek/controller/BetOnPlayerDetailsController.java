package com.project.sportsgeek.controller;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.*;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.BetOnPlayerDetailsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping(path = "/betOnPlayerDetails",produces = MediaType.APPLICATION_JSON_VALUE)
public class BetOnPlayerDetailsController {

    @Autowired
    BetOnPlayerDetailsService betOnPlayerDetailsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnPlayer.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<BetPlayerDetailsResponse>>> getAllBetOnPlayer() {
        Result<List<BetPlayerDetailsResponse>> playerList = betOnPlayerDetailsService.findAllBetOnPlayerDetails();
        return new ResponseEntity<>(playerList, HttpStatus.valueOf(playerList.getCode()));
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnPlayer.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetPlayerDetailsResponse>> getBetPlayerDetailsByBetPlayerId(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<BetPlayerDetailsResponse> playerList = betOnPlayerDetailsService.findBetPlayerDetailsByBetPlayerId(id);
        return new ResponseEntity<>(playerList, HttpStatus.valueOf(playerList.getCode()));
    }
    @GetMapping(value = "userId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnPlayer.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetPlayerDetailsResponse>> getBetPlayerDetailsByUserId(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<BetPlayerDetailsResponse> playerList = betOnPlayerDetailsService.findBetPlayerDetailsByUserId(id);
        return new ResponseEntity<>(playerList, HttpStatus.valueOf(playerList.getCode()));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = BetOnPlayer.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnPlayerDetails>> addBetPlayerDetails(@RequestBody(required = true) @Valid BetOnPlayerDetails betOnPlayerDetails) throws  Exception {
        Result<BetOnPlayerDetails> playerResult = betOnPlayerDetailsService.addBetOnPlayerDetails(betOnPlayerDetails);
        return new ResponseEntity(playerResult,HttpStatus.valueOf(playerResult.getCode()));
    }
    @PutMapping(value = "/{id}/{playerNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Venue.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnPlayerDetails>> updateBetPlayerDetails(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id, @PathVariable @Valid @Pattern(regexp = "[0-9]*") int playerNo, @RequestBody(required = true) @Valid BetOnPlayerDetails player) throws Exception {
        Result<BetOnPlayerDetails> playerResult = betOnPlayerDetailsService.updateBetPlayerDetails(id,playerNo, player);
        return new ResponseEntity(playerResult,HttpStatus.valueOf(playerResult.getCode()));
    }
    @PutMapping(value = "/playerPoints/{id}/{playerNo}/{playerPoints}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Venue.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnPlayerDetails>> updatePlayerPoints(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id, @PathVariable @Valid @Pattern(regexp = "[0-9]*") int playerNo, @PathVariable @Valid @Pattern(regexp = "[0-9]*") int playerPoints) throws Exception {
        Result<BetOnPlayerDetails> playerResult = betOnPlayerDetailsService.updatePlayerPoints(id, playerNo, playerPoints);
        return new ResponseEntity(playerResult,HttpStatus.valueOf(playerResult.getCode()));
    }
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = BetOnPlayer.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<BetOnPlayerDetails>> deleteBetPlayerDetailsById(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<Integer> playerResult =  betOnPlayerDetailsService.deleteBetPlayerDetails(id);
        return new ResponseEntity(playerResult,HttpStatus.valueOf(playerResult.getCode()));
    }
}
