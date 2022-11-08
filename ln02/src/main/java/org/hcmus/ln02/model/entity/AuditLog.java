package org.hcmus.ln02.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "request", columnDefinition = "TEXT")
  private String request;

  @Column(name = "response", columnDefinition = "TEXT")
  private String response;
}
