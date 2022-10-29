package org.hcmus.ln02.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.exception.ActorNotFound;
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

  @Override
  public Long updateActor(Actor actor) {
    Actor updatedActor = actorRepository.findById(actor.getId()).orElseThrow(() -> new ActorNotFound("Actor not found"));

    updatedActor.setFirstName(actor.getFirstName());
    updatedActor.setLastName(actor.getLastName());

    actorRepository.save(updatedActor);
    return updatedActor.getId();
  }
}
