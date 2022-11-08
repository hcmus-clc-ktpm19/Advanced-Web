package org.hcmus.ln02.aop;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hcmus.ln02.model.entity.AuditLog;
import org.hcmus.ln02.service.AuditLogService;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspect {

  @NonNull
  private final HttpServletRequest request;

  @NonNull
  private final HttpServletResponse response;

  @NonNull
  private final AuditLogService auditLogService;

  private AuditLog auditLog;

  @Pointcut("this(org.hcmus.ln02.controller.AbstractApplicationController)")
  private void abstractApplicationController() { /* Pointcut's alias */ }

  private Map<String, List<String>> getHeaders() {
    return Collections.list(request.getHeaderNames())
        .stream()
        .collect(Collectors.toMap(
            Function.identity(),
            h -> Collections.list(request.getHeaders(h))
        ));
  }

  private Map<String, Object> getRequest() {
    return Map.of(
        "headers", getHeaders(),
        "path", request.getRequestURI(),
        "method", request.getMethod()
    );
  }

  private Map<String, Object> getResponse(Object responseBody) {
    return Map.of(
        "status", response.getStatus(),
        "body", responseBody
    );
  }

  @Before("abstractApplicationController()")
  public void beforeAbstractApplicationController() {
    log.info("Before controller");
    auditLog = new AuditLog();
    auditLog.setRequest(getRequest().toString());
  }

  @AfterReturning(value = "abstractApplicationController()", returning = "result")
  public void afterAbstractApplicationController(Object result) {
    log.info("After controller");
    Map<String, Object> res = getResponse(result);
    auditLog.setResponse(res.toString());
    auditLogService.createAuditLog(auditLog);
  }
}
