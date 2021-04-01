package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository {
    public final JdbcTemplate template;

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
    }


    @Override

    public long create(Reservatie reservatie) {
        return 0;
    }

    @Override
    public List<Reservatie> findAll() {
        return null;
    }

    @Override
    public void delete(Reservatie klantId) {

    }
}
