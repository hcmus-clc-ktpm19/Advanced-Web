package org.hcmus.sakila.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.model.dto.ActorDto;
import org.hcmus.sakila.model.dto.FilmDto;
import org.hcmus.sakila.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Actor", description = "Actor API")
@RestController
@RequestMapping("api/v1/actors")
@RequiredArgsConstructor
public class ActorController extends AbstractApplicationController {

  private final ActorService actorService;

  @Operation(summary = "Get all actors")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDto.class))),
  })
  @GetMapping
  public List<ActorDto> getAllActors() {
    return actorService.getAllActors().stream()
        .map(applicationMapper::toActorDto)
        .collect(Collectors.toList());
  }

  @Operation(
      summary = "Save new actor",
      description = "Input first name and last name for saving new actor, id will be generated automatically. Return the saved actor's id"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDto.class))
      ),
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long saveActor(@RequestBody ActorDto actorDto) {
    return actorService.saveActor(applicationMapper.toActorEntity(actorDto));
  }

  @Operation(summary = "Update actor by id", description = "Update actor's first name and last name, id is required. Return the updated actor's id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {ActorDto.class, FilmDto.class}))),
      @ApiResponse(responseCode = "202", description = "custom description", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {ActorDto.class, FilmDto.class}))),
  })
  @PutMapping
  public Long updateActorById(@RequestBody ActorDto actorDto) {
    return actorService.updateActor(applicationMapper.toActorEntity(actorDto));
  }
}
