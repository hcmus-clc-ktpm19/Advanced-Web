package org.hcmus.ln02.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.dto.FilmDto;
import org.hcmus.ln02.model.entity.Film;
import org.hcmus.ln02.service.FilmService;
import org.hcmus.ln02.service.FilmTextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Tag(name = "Film", description = "Film API")
@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
@RestControllerAdvice
public class FilmController extends AbstractApplicationController {

  private final FilmService filmService;

  private final FilmTextService filmTextService;

  @GetMapping
  @Operation(summary = "Get all films")
  public ResponseEntity<List<FilmDto>> getAllFilms() {
    List<FilmDto> filmDtoList = filmService.getAllFilms().stream()
        .map(film -> applicationMapper.toFilmDto(film))
        .collect(Collectors.toList());
    return new ResponseEntity<>(filmDtoList, HttpStatus.OK);
  }

  @Operation(summary = "Get film by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the film",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Film.class))}
      ),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content
      )})
  @GetMapping("/{id}")
  public ResponseEntity<FilmDto> getFilmById(
      @Parameter(description = "id of film to be searched", required = true, example = "1")
      @PathVariable long id) {
    Film film = filmService.getFilmById(id);
    if (film == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(applicationMapper.toFilmDto(film), HttpStatus.OK);
  }

  @PostMapping
  @Operation(summary = "Insert a new film")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Inserted the film",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = FilmDto.class))
          })
  })
  public ResponseEntity<FilmDto> saveFilm(@Valid @RequestBody FilmDto filmDto) {
    FilmDto savedFilmDto = applicationMapper.toFilmDto(
        filmService.saveFilm(applicationMapper.toFilmEntity(filmDto)));
    return new ResponseEntity<>(savedFilmDto, HttpStatus.CREATED);
  }

  @DeleteMapping("/text/{id}")
  @Operation(summary = "Delete film text by id", description = "Delete a film text by its id, id is required")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The film text has been deleted", content = @Content),
  })
  @Parameter(description = "ID of film text to be deleted. ID must be a number", required = true, example = "1", name = "id")
  public void deleteFilmTextById(
      @PathVariable long id) {
    filmTextService.deleteFilmTextById(id);
  }
}
