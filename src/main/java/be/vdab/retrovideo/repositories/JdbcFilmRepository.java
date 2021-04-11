package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.domain.Stock;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcFilmRepository implements FilmRepository {
    private final JdbcTemplate template;
    private final RowMapper<Film> filmMapper =
            ((resultSet, i) -> new Film(resultSet.getLong("id"), resultSet.getLong("genreId"),
                    resultSet.getString("titel"), resultSet.getBigDecimal("prijs")));

    private final RowMapper<Stock> stockMapper =
            ((resultSet, i) -> new Stock(resultSet.getLong("voorraad")
                    , resultSet.getLong("gereserveerd")));

    public JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Film> findFilmById(long id) {
        var sql = "select id,genreid,titel,prijs from films where id = ?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findFilmsByGenreId(long genreId) {
        var sql = "select id,genreid,titel,prijs from films where genreid = ? " +
                "order by id";
        return template.query(sql, filmMapper, genreId);
    }

    @Override
    public List<Film> findFilmsByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = "select id,genreid,titel,prijs from films where id in (" +
                "?,".repeat(ids.size() - 1) +
                "?) order by id";
        return template.query(sql, filmMapper, ids.toArray());
    }

    @Override
    public BigDecimal findTotalePrijsByIds(Set<Long> ids) {
        if (ids.size() != 0) {
            var sql = "select sum(prijs) from films where id in (" +
                    "?,".repeat(ids.size() - 1) +
                    "?)";
            return template.queryForObject(sql, BigDecimal.class, ids.toArray());
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public Optional<Stock> findStockById(long id) {
        var sql = "select voorraad,gereserveerd from films where id = ?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, stockMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void verhoogGereserveerdWaardeMetEen(Reservatie reservatie) {
        var sql = "update films set gereserveerd=gereserveerd+1 where id=? and voorraad-gereserveerd>0";
        template.update(sql,reservatie.getFilmid());
    }

}
























