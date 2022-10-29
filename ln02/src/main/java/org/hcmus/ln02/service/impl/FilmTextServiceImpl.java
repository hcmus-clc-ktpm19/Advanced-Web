package org.hcmus.ln02.service.impl;

import lombok.AllArgsConstructor;
import org.hcmus.ln02.exception.FilmTextNotFoundException;
import org.hcmus.ln02.repository.FilmTextRepository;
import org.hcmus.ln02.service.FilmTextService;
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
