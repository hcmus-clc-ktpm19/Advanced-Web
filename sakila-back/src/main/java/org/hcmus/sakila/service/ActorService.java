package org.hcmus.sakila.service;


import java.util.List;
import org.hcmus.sakila.model.entity.Actor;

public interface ActorService {

  List<Actor> getAllActors();

  Long saveActor(Actor actor);

  Long updateActor(Actor actor);
}
