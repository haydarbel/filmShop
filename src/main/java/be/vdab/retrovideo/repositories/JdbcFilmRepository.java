package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.dao.DataAccessException;
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
                    resultSet.getString("titel"), resultSet.getBigDecimal("prijs"),
                    resultSet.getLong("voorraad"), resultSet.getLong("gereserveerd")));

    public JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Film> findFilmById(long id) {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films where id = ?";
        try {
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findFilmsByGenreId(long genreId) {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films where genreid = ? " +
                "order by id";
        return template.query(sql, filmMapper,genreId);
    }

    @Override
    public List<Film> findAllFilms() {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films order by id";
        return template.query(sql,filmMapper);
    }

    @Override
    public List<Film> findFilmsByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films where id in ("+
                "?,".repeat(ids.size() - 1)+
                "?) order by id";
        return template.query(sql,filmMapper,ids.toArray());
    }

    @Override
    public BigDecimal findTotalePrijsByIds(Set<Long> ids) {
        if (ids.size() != 0) {
            var sql = "select sum(prijs) from films where id in ("+
                    "?,".repeat(ids.size() - 1) +
                    "?)";
            return template.queryForObject(sql,BigDecimal.class,ids.toArray());
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public void eenStukvanVoorraadNaarGereserveerd(Reservatie reservatie) {
        var sql = "update films set voorraad = voorraad-1,gereserveerd=gereserveerd+1 " +
                "where id=? and voorraad>0";
        template.update(sql,reservatie.getFilmids());
    }

}
