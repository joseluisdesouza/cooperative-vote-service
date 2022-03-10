package com.api.cooperativegeneralmeeting.dtos;

import com.api.cooperativegeneralmeeting.models.Vote;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class ScheduleDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Set<Vote> votes;
}
