package com.project.sportsgeek.model;

import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gender {
	
	@QueryHelperPrimaryKey
    private int GenderId;
    private String Name;
}
