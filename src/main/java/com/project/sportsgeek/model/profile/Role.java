package com.project.sportsgeek.model.profile;

import javax.validation.constraints.NotNull;

import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@QueryHelperPrimaryKey
    private int RoleId;
    @NotNull
    private String Name;
}
