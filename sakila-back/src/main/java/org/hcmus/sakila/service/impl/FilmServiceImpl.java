package org.hcmus.sakila.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.model.entity.Film;
import org.hcmus.sakila.repository.FilmRepository;
import org.hcmus.sakila.service.FilmService;
import org.hcmus.sakila.util.HmacUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('FILM')")
public class FilmServiceImpl implements FilmService {

  @Value("${system-auth.http.auth-header-name}")
  private String principalRequestHeader;

  @Value("${system-auth.http.time-header-name}")
  private String timeRequestHeader;

  @Value("${system-auth.http.secret-key}")
  private String principalRequestValue;
  private final FilmRepository filmRepository;

  @Override
  public List<Film> getAllFilms() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add(timeRequestHeader, LocalDateTime.now().toString());
    HmacUtil hmacUtil = new HmacUtil(Constants.HMAC_ALGORITHMS, principalRequestValue, "/api/v1/films" + Objects.requireNonNull(
        headers.get(timeRequestHeader)).get(0));
    headers.add(principalRequestHeader, hmacUtil.hmacWithApacheCommons());
    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<Object[]> response = restTemplate.exchange(Constants.SAKILA_SERVICE_URL + "/films", HttpMethod.GET, request, Object[].class);
    Object[] objects = response.getBody();
    ObjectMapper mapper = new ObjectMapper();
    return Arrays.stream(Objects.requireNonNull(objects))
        .map(object -> mapper.convertValue(object, Film.class))
        .collect(Collectors.toList());
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
