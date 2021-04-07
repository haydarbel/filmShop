package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.domain.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository {
    Optional<Film> findFilmById(long id);
    List<Film> findFilmsByGenreId(long genreId);
    List<Film> findFilmsByIds(Set<Long> ids);
    BigDecimal findTotalePrijsByIds(Set<Long> ids);
    Optional<Stock> findStockById(long id);
    void verhoogGereserveerdWaardeMetEen(Reservatie reservatie);
}

