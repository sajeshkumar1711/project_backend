package com.project.sportsgeek.model;

import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Recharge implements Serializable {
    @QueryHelperPrimaryKey
    private int RechargeId;
    private int UserId;
    private String UserName;
    @NotNull
    private  int Points;
    private Timestamp RechargeDate;
}
