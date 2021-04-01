package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface FilmService {
    Optional<Film> findFilmById(long id);
    List<Film> findAllFilms();
    List<Genre> findAllGenres();
    Optional<Genre> findGenreById(long id);
    List<Film> findFilmsByGenre(long id);

}
