package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

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
    public Optional<Film> findById(long id) {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films where id = ?";
        try {
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findByGenreId(long genreId) {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films where genreid = ? " +
                "order by id";
        return template.query(sql, filmMapper,genreId);
    }

    @Override
    public List<Film> findAll() {
        var sql = "select id,genreid,titel,voorraad,gereserveerd,prijs from films order by id";
        return template.query(sql,filmMapper);
    }
}
