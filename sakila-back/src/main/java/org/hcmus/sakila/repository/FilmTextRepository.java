package org.hcmus.sakila.repository;

import org.hcmus.sakila.model.entity.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmTextRepository extends JpaRepository<FilmText, Long> {

}
