package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.domain.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface FilmService {
    Optional<Film> findFilmById(long id);

    List<Film> findFilmsByGenreId(long id);

    List<Film> findFilmsByIds(Set<Long> ids);

    BigDecimal findTotalePrijsByIds(Set<Long> ids);

    Optional<Stock> findStockById(long id);

    List<Genre> findAllGenres();
}
