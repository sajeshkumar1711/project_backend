package com.project.sportsgeek.model.profile;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAtLogin {

	@NotNull
    private String Username;
    @NotNull
    private String Password;
}
