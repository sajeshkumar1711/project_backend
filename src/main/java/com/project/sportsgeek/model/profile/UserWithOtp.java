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
public class UserWithOtp implements Serializable {
    private int UserId;
    private String Password;
    private int Otp;
}
