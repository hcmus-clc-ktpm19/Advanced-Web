package org.hcmus.ln02.service;

import java.util.List;
import org.hcmus.ln02.model.entity.Film;


public interface FilmService {
  List<Film> getAllFilms();
  Film getFilmById(Long id);
  Film saveFilm(Film film);
}
