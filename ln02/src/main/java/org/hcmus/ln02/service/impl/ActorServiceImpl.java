package org.hcmus.ln02.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.entity.Actor;
import org.hcmus.ln02.repository.ActorRepository;
import org.hcmus.ln02.service.ActorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

  private final ActorRepository actorRepository;

  @Override
  public List<Actor> getAllActors() {
    return actorRepository.findAll();
  }

  @Override
  public Long saveActor(Actor actor) {
    actorRepository.saveAndFlush(actor);
    return actor.getId();
  }
}
