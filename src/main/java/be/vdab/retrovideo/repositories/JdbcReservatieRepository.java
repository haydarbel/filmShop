package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public int[] create(Reservatie reservatie) {
        return template.batchUpdate(
                "INSERT INTO reservaties (klantid,filmid,reservatie) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, reservatie.getKlantId());
                        ps.setLong(2, reservatie.getFilmids().get(i));
                        ps.setDate(3, Date.valueOf(reservatie.getDatum()));
                    }
                    @Override
                    public int getBatchSize() {
                        return reservatie.getFilmids().size();
                    }
                }
        );
    }

}
















