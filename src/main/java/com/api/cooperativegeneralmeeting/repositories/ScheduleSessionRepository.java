package com.api.cooperativegeneralmeeting.repositories;

import com.api.cooperativegeneralmeeting.models.ScheduleSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleSessionRepository extends JpaRepository<ScheduleSession, Long> {
}
