package com.project.sportsgeek.model.profile;

import javax.validation.constraints.Min;
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
public class UserWithNewPassword {

	@Min(1)
    private int UserId;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
