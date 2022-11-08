package org.hcmus.ln02.service;

import org.hcmus.ln02.model.entity.AuditLog;

public interface AuditLogService {
  Long createAuditLog(AuditLog auditLog);
}
