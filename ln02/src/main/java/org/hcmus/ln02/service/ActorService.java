package org.hcmus.ln02.service;


import java.util.List;
import org.hcmus.ln02.model.entity.Actor;

public interface ActorService {

  List<Actor> getAllActors();

  Long saveActor(Actor actor);

  Long updateActor(Actor actor);
}
