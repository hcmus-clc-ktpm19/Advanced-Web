package org.hcmus.ln02.service.impl;

import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.hcmus.ln02.service.AuditLogService;
import org.hcmus.ln02.service.SchedulerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

  private final AuditLogService auditLogService;

  // Clean every 30 seconds
  @Override
  @Scheduled(timeUnit = TimeUnit.SECONDS, initialDelay = 30, fixedRate = 30)
  public void cleanLog() {
    auditLogService.deleteAuditLogs();
  }
}
