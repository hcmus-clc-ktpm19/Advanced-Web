package org.hcmus.sakila.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.model.entity.Film;
import org.hcmus.sakila.repository.FilmRepository;
import org.hcmus.sakila.service.FilmService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
  private final FilmRepository filmRepository;

  @Override
  public List<Film> getAllFilms() {
    return filmRepository.findAll();
  }

  @Override
  public Film getFilmById(Long id) {
    return filmRepository.findById(id).orElse(null);
  }

  @Override
  public Film saveFilm(Film film) {
    return filmRepository.saveAndFlush(film);
  }
}
