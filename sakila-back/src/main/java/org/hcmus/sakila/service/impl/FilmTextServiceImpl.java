package org.hcmus.sakila.service.impl;

import lombok.AllArgsConstructor;
import org.hcmus.sakila.exception.FilmTextNotFoundException;
import org.hcmus.sakila.repository.FilmTextRepository;
import org.hcmus.sakila.service.FilmTextService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmTextServiceImpl implements FilmTextService {

  private final FilmTextRepository filmTextRepository;

  @Override
  public void deleteFilmTextById(Long id) {
    if (!filmTextRepository.existsById(id)) {
      throw new FilmTextNotFoundException("Film text not found");
    }
    filmTextRepository.deleteById(id);
  }
}
