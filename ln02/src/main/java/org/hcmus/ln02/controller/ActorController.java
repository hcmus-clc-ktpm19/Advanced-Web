package org.hcmus.ln02.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.dto.ActorDto;
import org.hcmus.ln02.service.ActorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/actor")
@RequiredArgsConstructor
public class ActorController extends AbstractApplicationController {

  private final ActorService actorService;

  @GetMapping("get-all")
  public List<ActorDto> getAllActors() {
    return actorService.getAllActors().stream()
        .map(applicationMapper::toActorDto)
        .collect(Collectors.toList());
  }

  @PostMapping("save")
  public Long saveActor(@RequestBody ActorDto actorDto) {
    return actorService.saveActor(applicationMapper.toActorEntity(actorDto));
  }
}
