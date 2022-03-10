package com.api.cooperativegeneralmeeting.services;

import com.api.cooperativegeneralmeeting.models.Schedule;
import com.api.cooperativegeneralmeeting.models.ScheduleSession;
import com.api.cooperativegeneralmeeting.models.Vote;
import com.api.cooperativegeneralmeeting.repositories.ScheduleRepository;
import com.api.cooperativegeneralmeeting.repositories.ScheduleSessionRepository;
import com.api.cooperativegeneralmeeting.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleSessionRepository scheduleSessionRepository;
    private final VoteRepository voteRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Page<Schedule> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    public boolean existsByTitle(String title) {
        return scheduleRepository.existsByTitle(title);
    }

    public ScheduleSession saveSession(ScheduleSession scheduleSession) {
        return scheduleSessionRepository.save(scheduleSession);
    }

    public boolean existsByCpf(String cpf) {
        return voteRepository.existsByCpf(cpf);
    }

    public Optional<ScheduleSession> getBySession(Long id) {
        return scheduleSessionRepository.findById(id);
    }

    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

}
