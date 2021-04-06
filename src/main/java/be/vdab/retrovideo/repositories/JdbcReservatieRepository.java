package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository {
    private final SimpleJdbcInsert insert;
    public final JdbcTemplate template;

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template)
                .withTableName("reservaties")
                .usingColumns("klantid", "filmid", "reservatie");
    }

    @Override
    public int createReservatie(Reservatie reservatie) {
        try {
            return insert.execute(Map.of("klantid", reservatie.getKlantId(), "filmid", reservatie.getFilmid(),
                    "reservatie", reservatie.getDatum()));
        } catch (DuplicateKeyException e) {
            return 0;
        }
    }

}
















