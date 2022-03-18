package com.api.cooperativegeneralmeeting.repositories;

import com.api.cooperativegeneralmeeting.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    boolean existsByTitle(String title);
}
