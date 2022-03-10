package com.api.cooperativegeneralmeeting.dtos;

import com.api.cooperativegeneralmeeting.enums.VoteStatus;
import com.api.cooperativegeneralmeeting.models.Schedule;
import lombok.Data;

@Data
public class VoteDto {

    private VoteStatus voteStatus;
    private String cpf;
    private Schedule schedule;
}
