package com.project.sportsgeek.model;

import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicChat {
    @QueryHelperPrimaryKey
    private int publicChatId;
    private int userId;
    private String message;
    private boolean status;
    private Timestamp chatTimestamp;
}
