package org.hcmus.sakila.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.exception.ActorNotFound;
import org.hcmus.sakila.model.entity.Actor;
import org.hcmus.sakila.repository.ActorRepository;
import org.hcmus.sakila.service.ActorService;
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
