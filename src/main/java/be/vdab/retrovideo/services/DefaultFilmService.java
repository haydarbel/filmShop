package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        return filmRepository.findById(id);
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
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
    public List<Film> findFilmsByGenre(long id) {
        return filmRepository.findByGenreId(id);
    }
}
