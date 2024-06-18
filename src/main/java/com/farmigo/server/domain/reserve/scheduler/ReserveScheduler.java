package com.farmigo.server.domain.reserve.scheduler;

import com.farmigo.server.domain.reserve.repository.UserActivityReserveMongoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Description : 예약 관련 스케줄링
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ReserveScheduler {

//    private final TaskScheduler scheduler;
//    private final UserActivityReserveMongoRepository userActivityReserveMongoRepository;
//
//    @PostConstruct
//    public void init() {
//        log.info("ChatScheduler init");
//        scheduler.scheduleWithFixedDelay(this::reserveScheduler, Duration.ofSeconds(10));
//    }
//
//    public void reserveScheduler() {
//        log.info("ChatScheduler chatScheduler");
//    }
}
