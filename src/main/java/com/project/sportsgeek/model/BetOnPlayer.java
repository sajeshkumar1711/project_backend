package com.project.sportsgeek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.sportsgeek.annotations.QueryHelperColumnName;
import com.project.sportsgeek.annotations.QueryHelperPrimaryKey;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "BetOnPlayer Model")
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BetOnPlayer implements Serializable {

    @QueryHelperColumnName(name = "BetPlayerId")
    @QueryHelperPrimaryKey
    private int betPlayerId;
    private int userId;
    private int matchId;
    private int totalGamePoints;
}
