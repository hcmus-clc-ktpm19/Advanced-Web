package org.hcmus.ln02.service.impl;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import lombok.AllArgsConstructor;
import org.hcmus.ln02.model.entity.AuditLog;
import org.hcmus.ln02.repository.AuditLogRepository;
import org.hcmus.ln02.service.AuditLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Throwable.class, propagation = REQUIRES_NEW)
public class AuditLogServiceImpl implements AuditLogService {

  private final AuditLogRepository auditLogRepository;

  @Override
  public Long createAuditLog(AuditLog auditLog) {
    auditLogRepository.saveAndFlush(auditLog);
    return auditLog.getId();
  }

  @Override
  public void deleteAuditLogs() {
    auditLogRepository.deleteAll();
  }
}
