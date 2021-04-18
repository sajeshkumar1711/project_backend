package com.project.sportsgeek.model.profile;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWinningAndLossingPoints implements Serializable {

	private int UserId;
    private int WinningPoints;
    private int LoosingPoints;
}
