package com.api.cooperativegeneralmeeting.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleSessionDto {

    private Long idSchedule;
    private LocalDateTime registrationDateOpeningSession;

}
