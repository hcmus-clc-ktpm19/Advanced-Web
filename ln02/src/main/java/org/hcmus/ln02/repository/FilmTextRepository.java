package org.hcmus.ln02.repository;

import org.hcmus.ln02.model.entity.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmTextRepository extends JpaRepository<FilmText, Long> {

}
