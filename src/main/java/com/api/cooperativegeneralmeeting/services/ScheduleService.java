package com.api.cooperativegeneralmeeting.services;

import com.api.cooperativegeneralmeeting.dtos.ScheduleDto;
import com.api.cooperativegeneralmeeting.enums.SessionStatus;
import com.api.cooperativegeneralmeeting.models.Schedule;
import com.api.cooperativegeneralmeeting.models.ScheduleSession;
import com.api.cooperativegeneralmeeting.models.Vote;
import com.api.cooperativegeneralmeeting.repositories.ScheduleRepository;
import com.api.cooperativegeneralmeeting.repositories.ScheduleSessionRepository;
import com.api.cooperativegeneralmeeting.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleSessionRepository scheduleSessionRepository;
    private final VoteRepository voteRepository;

    public ResponseEntity<Object> save(Schedule schedule) {
        findById(schedule.getId());
        if (scheduleRepository.existsByTitle(schedule.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This schedule with this name already exists");
        }
        schedule.setSessionStatus(SessionStatus.OPEN);
        if (schedule.getTimeLimit() == null) {
            schedule.setTimeLimit(1);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleRepository.save(schedule));
    }

    public Page<Schedule> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public Schedule findById(final Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    public void delete(Long id) {
        findById(id);
        scheduleRepository.deleteById(id);
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

    private LocalDateTime getTimeLimit(ScheduleDto scheduleDto) {
        return LocalDateTime.now().plusMinutes(scheduleDto.getTimeLimit());
    }

}
