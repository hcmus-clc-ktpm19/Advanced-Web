package org.hcmus.sakila.controller;

import org.hcmus.sakila.util.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractApplicationController {
  @Autowired
  protected ApplicationMapper applicationMapper;
}
