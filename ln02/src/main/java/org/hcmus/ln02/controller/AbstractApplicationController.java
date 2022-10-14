package org.hcmus.ln02.controller;

import org.hcmus.ln02.util.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractApplicationController {
  @Autowired
  protected ApplicationMapper applicationMapper;
}
