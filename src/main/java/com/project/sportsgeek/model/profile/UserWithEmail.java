package com.project.sportsgeek.model.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithEmail {

	private int EmailContactId;
    private int UserId;
    private String EmailId;
}
