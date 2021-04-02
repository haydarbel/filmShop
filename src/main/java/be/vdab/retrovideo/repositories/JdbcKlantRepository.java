package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcKlantRepository implements KlantRepository {
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantMapper =
            ((resultSet, i) -> new Klant(resultSet.getLong("id"), resultSet.getString("familienaam"),
                    resultSet.getString("voornaam"), resultSet.getString("straatnummer"),
                    resultSet.getString("postcode"), resultSet.getString("gemeente")));

    public JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Klant> findById(long id) {
        var sql = "select id,familienaam,voornaam,straatnummer,postcode,gemeente" +
                " from klanten  where id = ?";
        try {
            return Optional.of(template.queryForObject(sql, klantMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Klant> findAll() {
        var sql = "select id,familienaam,voornaam,straatnummer,postcode,gemeente from klanten order by familienaam";
        return template.query(sql, klantMapper);
    }

    @Override
    public List<Klant> findByLetters(String tekst) {
        var sql = "select id,familienaam,voornaam,straatnummer,postcode,gemeente" +
                " from klanten where familienaam like ? order by familienaam";
        return template.query(sql, klantMapper, '%' + tekst + '%');

    }
}
