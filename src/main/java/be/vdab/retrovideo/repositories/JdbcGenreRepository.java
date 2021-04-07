package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreRepository implements GenreRepository {
    private final JdbcTemplate template;
    private final RowMapper<Genre> genreMapper =
            (((resultSet, i) -> new Genre(resultSet.getLong("id"),
                    resultSet.getString("naam"))));

    public JdbcGenreRepository(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<Genre> findAll() {
        var sql = "select id,naam from genres order by naam";
        return template.query(sql, genreMapper);
    }

}
