package com.api.cooperativegeneralmeeting.repositories;

import com.api.cooperativegeneralmeeting.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByCpf(String cpf);
}
