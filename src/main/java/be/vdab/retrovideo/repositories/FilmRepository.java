package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    Optional<Film> findById(long id);
    List<Film> findByGenreId(long genreId);
    List<Film> findAll();
}

