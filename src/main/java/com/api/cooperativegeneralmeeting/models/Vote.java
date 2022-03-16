package com.api.cooperativegeneralmeeting.models;

import com.api.cooperativegeneralmeeting.enums.VoteStatus;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAssociate;
    private VoteStatus voteStatus;

    @Column(length = 11)
    private String cpf;

//    @OneToMany
//    private Schedule schedule;

}
