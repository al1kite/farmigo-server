package com.farmigo.server.domain.chat.scheduler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Description :
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ChatScheduler {

//    private final TaskScheduler scheduler;
//
//    @PostConstruct
//    public void init() {
//        log.info("ChatScheduler init");
//        scheduler.scheduleWithFixedDelay(this::chatScheduler, Duration.ofMinutes(1));
//    }
//
//    public void chatScheduler() {
//        log.info("ChatScheduler chatScheduler");
//    }
}
