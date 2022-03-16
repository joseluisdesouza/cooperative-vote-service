package com.api.cooperativegeneralmeeting.resources;

import com.api.cooperativegeneralmeeting.dtos.ScheduleDto;
import com.api.cooperativegeneralmeeting.dtos.ScheduleSessionDto;
import com.api.cooperativegeneralmeeting.dtos.VoteDto;
import com.api.cooperativegeneralmeeting.enums.SessionStatus;
import com.api.cooperativegeneralmeeting.models.Schedule;
import com.api.cooperativegeneralmeeting.models.ScheduleSession;
import com.api.cooperativegeneralmeeting.models.Vote;
import com.api.cooperativegeneralmeeting.services.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/schedule")
@Api(value = "API REST SCHEDULE")
public class ScheduleResource {

    private final ScheduleService scheduleService;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleResource.class);

    @PostMapping
    @Operation(summary = "Create schedule",
            responses = {@ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ScheduleDto.class)))})
    public ResponseEntity<Object> saveSchedule(@RequestBody @Valid ScheduleDto scheduleDto) {

        logger.info("Call to create schedule");

        var scheduleModel = new Schedule();
        BeanUtils.copyProperties(scheduleDto, scheduleModel);
        scheduleModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        var responseEntityBody = scheduleService.save(scheduleModel);

        logger.info("Schedule created successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntityBody);

    }

    @GetMapping
    @Operation(summary = "Returning all schedules",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ScheduleDto.class)))})
    public ResponseEntity<Page<Schedule>> getAllSchedule(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        logger.info("Returning all schedules");
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returning schedule by id",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ScheduleDto.class)))})
    public ResponseEntity<Object> getByScheduleId(@PathVariable Long id) {
        Schedule scheduleServiceById = scheduleService.findById(id);

        logger.info("Returning schedule by id {}.", id);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleServiceById);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ScheduleDto.class)))})
    public ResponseEntity<Object> deleteSchedule(@PathVariable Long id) {

        scheduleService.delete(id);

        logger.info("Schedule deleted");

        return ResponseEntity.status(HttpStatus.OK).body("Schedule deleted with successfully");
    }

    @PostMapping("/{idSchedule}/session")
    @Operation(summary = "Open session in schedule",
            responses = {@ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ScheduleSessionDto.class)))})
    public ResponseEntity<Object> openingSession(@PathVariable Long idSchedule, @RequestBody ScheduleSessionDto scheduleSessionDto, Pageable pageable) {

        logger.info("Opening session...");

        getAllSchedule(pageable);

        var scheduleSessionModel = new ScheduleSession();
        BeanUtils.copyProperties(scheduleSessionDto, scheduleSessionModel);
        scheduleSessionModel.setRegistrationDateOpeningSession(LocalDateTime.now(ZoneId.of("UTC")));

        scheduleSessionModel.setSessionStatus(SessionStatus.OPEN);

        ResponseEntity<Object> objectResponseEntity = ResponseEntity.status(HttpStatus.CREATED)
                .body(scheduleService.saveSession(scheduleSessionModel));

        logger.info("Session created");
        return objectResponseEntity;
    }

    @PostMapping("/{idSchedule}/vote")
    @Operation(summary = "Vote in schedule",
            responses = {@ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = VoteDto.class)))})
    public ResponseEntity<Object> vote(@PathVariable Long idSchedule, @RequestBody VoteDto voteDto) {
        if (scheduleService.existsByCpf(voteDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This member has already voted");
        }

        var voteModel = new Vote();
        BeanUtils.copyProperties(voteDto, voteModel);

        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveVote(voteModel));
        
        return responseEntity;

    }
}
