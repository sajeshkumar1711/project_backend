package com.project.sportsgeek.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BetOnTeamWithUser {
    private int BetTeamId;
    private int UserId;
    private int MatchId;
    private int TeamId;
    private String ShortName;
    private int BetPoints;
    private int WinningPoints;
    private String FirstName;
    private String LastName;
    private String Username;
    private String ProfilePicture;
}
