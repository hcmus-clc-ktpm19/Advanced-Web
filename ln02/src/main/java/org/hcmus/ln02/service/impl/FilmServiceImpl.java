package org.hcmus.ln02.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hcmus.ln02.model.entity.Film;
import org.hcmus.ln02.repository.FilmRepository;
import org.hcmus.ln02.service.FilmService;
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
  public Film saveFilm(Film film) {
    return filmRepository.saveAndFlush(film);
  }
}
