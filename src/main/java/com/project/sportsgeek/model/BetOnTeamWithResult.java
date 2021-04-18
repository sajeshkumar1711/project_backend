package com.project.sportsgeek.model;

import java.io.Serializable;

public class BetOnTeamWithResult implements Serializable {
    private int BetTeamId;
    private String TeamShortName;
    private String FirstName;
    private String LastName;
    private String Username;
    private String ProfilePicture;
    private int BetPoints;
    private int WinningPoints;

    public BetOnTeamWithResult() {
    }

    public BetOnTeamWithResult(int betTeamId, String teamShortName, String firstName, String lastName, String username, String profilePicture, int betPoints, int winningPoints) {
        BetTeamId = betTeamId;
        TeamShortName = teamShortName;
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        ProfilePicture = profilePicture;
        BetPoints = betPoints;
        WinningPoints = winningPoints;
    }

    public int getBetTeamId() {
        return BetTeamId;
    }

    public void setBetTeamId(int betTeamId) {
        BetTeamId = betTeamId;
    }

    public String getTeamShortName() {
        return TeamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        TeamShortName = teamShortName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public int getBetPoints() {
        return BetPoints;
    }

    public void setBetPoints(int betPoints) {
        BetPoints = betPoints;
    }

    public int getWinningPoints() {
        return WinningPoints;
    }

    public void setWinningPoints(int winningPoints) {
        WinningPoints = winningPoints;
    }
}
