package com.flutter.alloffootball.common.batch;

import com.flutter.alloffootball.common.batch.service.MatchStatisticsService;
import com.flutter.alloffootball.common.batch.service.UserStatisticsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // Scheduler 활성화
@Slf4j
@RequiredArgsConstructor
public class BatchSchedulerConfig {

    private final MatchStatisticsService matchStatisticsService;
    private final UserStatisticsService userStatisticsService;

    @PostConstruct
    public void initialMatchStatisticsSchedule() {
        log.info("InitialMatchStatisticsSchedule");
        matchStatisticsService.refresh();

        log.info("UserStatisticsSchedule");
        userStatisticsService.refresh();
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void testSchedule() {
        System.out.println("스케쥴 작동");
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void userStatisticsSchedule() {
        log.info("UserStatisticsSchedule");
        userStatisticsService.refresh();
    }
}
