package org.hcmus.ln02.service;

import java.util.List;
import org.hcmus.ln02.model.entity.Actor;

public interface ActorService {
  List<Actor> getAllActors();

  long saveActor(Actor actor);
}
