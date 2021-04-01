package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class DefaultFilmService implements FilmService{
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public DefaultFilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Film> findFilmById(long id) {
        return filmRepository.findFilmById(id);
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAllFilms();
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findGenreById(long id) {
        return genreRepository.findbyId(id);
    }

    @Override
    public List<Film> findFilmsByGenreId(long id) {
        return filmRepository.findFilmsByGenreId(id);
    }

    @Override
    public List<Film> findFilmsByIds(Set<Long> ids) {
        return filmRepository.findFilmsByIds(ids);
    }

    @Override
    public BigDecimal findTotalePrijsByIds(Set<Long> ids) {
        return filmRepository.findTotalePrijsByIds(ids);
    }
}
