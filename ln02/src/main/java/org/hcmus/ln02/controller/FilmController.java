package org.hcmus.ln02.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.dto.FilmDto;
import org.hcmus.ln02.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController extends AbstractApplicationController {

  private final FilmService filmService;

  @GetMapping
  public ResponseEntity<List<FilmDto>> getAllFilms() {
    List<FilmDto> filmDtoList = filmService.getAllFilms().stream()
        .map(film -> applicationMapper.toFilmDto(film))
        .collect(Collectors.toList());
    return new ResponseEntity<>(filmDtoList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<FilmDto> saveFilm(@Valid @RequestBody FilmDto filmDto) {
    FilmDto savedFilmDto = applicationMapper.toFilmDto(filmService.saveFilm(applicationMapper.toFilmEntity(filmDto)));
    return new ResponseEntity<>(savedFilmDto, HttpStatus.CREATED);
  }
}
