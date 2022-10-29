package org.hcmus.ln02.util.mapper;

import java.time.LocalDateTime;
import org.hcmus.ln02.model.dto.ActorDto;
import org.hcmus.ln02.model.dto.CategoryDto;
import org.hcmus.ln02.model.dto.ErrorDto;
import org.hcmus.ln02.model.dto.FilmDto;
import org.hcmus.ln02.model.entity.Actor;
import org.hcmus.ln02.model.entity.Category;
import org.hcmus.ln02.model.entity.Film;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class ApplicationMapper {

  public ActorDto toActorDto(Actor actor) {
    if (actor == null) {
      return null;
    } else {
      ActorDto actorDto = new ActorDto();
      actorDto.setId(actor.getId());
      actorDto.setFirstName(actor.getFirstName());
      actorDto.setLastName(actor.getLastName());
      return actorDto;
    }
  }

  public Actor toActorEntity(ActorDto actorDto) {
    if (actorDto == null) {
      return null;
    } else {
      Actor actor = new Actor();
      actor.setId(actorDto.getId());
      actor.setFirstName(actorDto.getFirstName());
      actor.setLastName(actorDto.getLastName());
      return actor;
    }
  }

  public FilmDto toFilmDto(Film film) {
    if (film == null) {
      return null;
    } else {
      FilmDto filmDto = new FilmDto();
      filmDto.setFilmId(film.getFilmId());
      filmDto.setTitle(film.getTitle());
      filmDto.setDescription(film.getDescription());
      filmDto.setReleaseYear(film.getReleaseYear());
      filmDto.setLanguageId(film.getLanguageId());
      filmDto.setOriginalLanguageId(film.getOriginalLanguageId());
      filmDto.setRentalDuration(film.getRentalDuration());
      filmDto.setRentalRate(film.getRentalRate());
      filmDto.setLength(film.getLength());
      filmDto.setReplacementCost(film.getReplacementCost());
      filmDto.setRating(film.getRating());
      filmDto.setSpecialFeatures(film.getSpecialFeatures());
      return filmDto;
    }
  }

  public Film toFilmEntity(FilmDto filmDto) {
    if (filmDto == null) {
      return null;
    } else {
      Film film = new Film();
      film.setTitle(filmDto.getTitle());
      film.setDescription(filmDto.getDescription());
      film.setReleaseYear(filmDto.getReleaseYear());
      film.setLanguageId(filmDto.getLanguageId());
      film.setOriginalLanguageId(filmDto.getOriginalLanguageId());
      film.setRentalDuration(filmDto.getRentalDuration());
      film.setRentalRate(filmDto.getRentalRate());
      film.setLength(filmDto.getLength());
      film.setReplacementCost(filmDto.getReplacementCost());
      film.setRating(filmDto.getRating());
      film.setSpecialFeatures(filmDto.getSpecialFeatures());
      return film;
    }
  }

  public CategoryDto toCategoryDto(Category category) {
    if (category == null) {
      return null;
    } else {
      CategoryDto categoryDto = new CategoryDto();
      categoryDto.setCategoryId(category.getCategoryId());
      categoryDto.setName(category.getName());
      categoryDto.setLastUpdate(category.getLastUpdate());
      return categoryDto;
    }
  }

  public Category toCategoryEntity(CategoryDto categoryDto) {
    if (categoryDto == null) {
      return null;
    } else {
      Category category = new Category();
      category.setCategoryId(categoryDto.getCategoryId());
      category.setName(categoryDto.getName());
      category.setLastUpdate(categoryDto.getLastUpdate());
      return category;
    }
  }

  public ErrorDto toErrorDto(LocalDateTime timestamp, HttpStatus status, String error, String message, String path) {
    ErrorDto errorDto = new ErrorDto();
    errorDto.setTimestamp(timestamp);
    errorDto.setStatus(status);
    errorDto.setError(error);
    errorDto.setMessage(message);
    errorDto.setPath(path);
    return errorDto;
  }
}
