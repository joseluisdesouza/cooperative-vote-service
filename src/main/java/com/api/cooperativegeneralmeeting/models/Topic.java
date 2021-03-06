package com.api.cooperativegeneralmeeting.models;

import com.api.cooperativegeneralmeeting.enums.ApprovedOrNotApproved;
import com.api.cooperativegeneralmeeting.enums.SessionStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_schedule")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
    private SessionStatus sessionStatus;
    private Integer timeLimit;
    private Integer resultVote;
    private Boolean approved;
    private LocalDateTime endSession;

}
