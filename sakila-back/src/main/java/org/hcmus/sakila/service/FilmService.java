package org.hcmus.sakila.service;

import java.util.List;
import org.hcmus.sakila.model.entity.Film;


public interface FilmService {
  List<Film> getAllFilms();
  Film getFilmById(Long id);
  Film saveFilm(Film film);
}
