package com.api.cooperativegeneralmeeting.models;

import com.api.cooperativegeneralmeeting.enums.SessionStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_schedule_session")
public class ScheduleSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime registrationDateOpeningSession;
    private SessionStatus sessionStatus;

}
