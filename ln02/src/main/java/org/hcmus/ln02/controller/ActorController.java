package org.hcmus.ln02.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.dto.ActorDto;
import org.hcmus.ln02.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/actors")
@RequiredArgsConstructor
public class ActorController extends AbstractApplicationController {

  private final ActorService actorService;

  @GetMapping
  public List<ActorDto> getAllActors() {
    return actorService.getAllActors().stream()
        .map(applicationMapper::toActorDto)
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long saveActor(@RequestBody ActorDto actorDto) {
    return actorService.saveActor(applicationMapper.toActorEntity(actorDto));
  }

  @PutMapping
  public Long updateActorById(@RequestBody ActorDto actorDto) {
    return actorService.updateActor(applicationMapper.toActorEntity(actorDto));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteActorById(@PathVariable Long id) {
    actorService.deleteActor(id);
  }
}
