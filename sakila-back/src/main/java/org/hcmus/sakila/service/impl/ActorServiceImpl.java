package org.hcmus.sakila.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.exception.ActorNotFound;
import org.hcmus.sakila.model.entity.Actor;
import org.hcmus.sakila.repository.ActorRepository;
import org.hcmus.sakila.service.ActorService;
import org.hcmus.sakila.util.HmacUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

  @Value("${system-auth.http.auth-header-name}")
  private String principalRequestHeader;

  @Value("${system-auth.http.time-header-name}")
  private String timeRequestHeader;

  @Value("${system-auth.http.secret-key}")
  private String principalRequestValue;

  private final ActorRepository actorRepository;


  @Override
  public List<Actor> getAllActors() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add(timeRequestHeader, LocalDateTime.now().toString());
    HmacUtil hmacUtil = new HmacUtil(Constants.HMAC_ALGORITHMS, principalRequestValue, "/api/v1/actors" + Objects.requireNonNull(
        headers.get(timeRequestHeader)).get(0));
    headers.add(principalRequestHeader, hmacUtil.hmacWithApacheCommons());
    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<Object[]> response = restTemplate.exchange(Constants.SAKILA_SERVICE_URL + "/actors", HttpMethod.GET, request, Object[].class);
    Object[] objects = response.getBody();
    ObjectMapper mapper = new ObjectMapper();
    return Arrays.stream(Objects.requireNonNull(objects))
        .map(object -> mapper.convertValue(object, Actor.class))
        .collect(Collectors.toList());
  }

  @Override
  public Long saveActor(Actor actor) {
    actorRepository.saveAndFlush(actor);
    return actor.getId();
  }

  @Override
  public Long updateActor(Actor actor) {
    Actor updatedActor = actorRepository.findById(actor.getId()).orElseThrow(() -> new ActorNotFound("Actor not found"));

    updatedActor.setFirstName(actor.getFirstName());
    updatedActor.setLastName(actor.getLastName());

    actorRepository.save(updatedActor);
    return updatedActor.getId();
  }
}
