package org.hcmus.ln02.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.hcmus.ln02.model.entity.Actor;
import org.hcmus.ln02.repository.ActorRepository;
import org.hcmus.ln02.service.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {

  @Mock
  private ActorRepository actorRepository;
  private ActorService actorService;

  @BeforeEach
  void setUp() {
    actorService = new ActorServiceImpl(actorRepository);
  }

  @Test
  void getAllActors() {
    when(actorRepository.findAll()).thenReturn(List.of(new Actor()));
    assertFalse(actorService.getAllActors().isEmpty());
  }

  @Test
  void insertActor() {
  }
}