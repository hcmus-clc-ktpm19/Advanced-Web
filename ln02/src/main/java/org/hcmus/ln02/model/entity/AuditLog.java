package org.hcmus.ln02.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hcmus.ln02.util.converter.HashMapConverter;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "request", columnDefinition = "JSON")
  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> request;

  @Column(name = "response", columnDefinition = "JSON")
  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> response;

  @Column(name = "request_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime requestAt = LocalDateTime.now();
}
