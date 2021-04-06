package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.ReservatieForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcReservatieRepository.class)
@Sql("/insertReservaties.sql")
class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String RESERVATIES = "reservaties";
    private final JdbcReservatieRepository repository;

    JdbcReservatieRepositoryTest(JdbcReservatieRepository repository) {
        this.repository = repository;
    }

//    @Test
//    void create() {
//        var reservatie = new ReservatieForm(4, 3);
//        assertThat(repository.createReservatie(reservatie)).isEqualTo(1);
//        assertThat(countRowsInTableWhere(RESERVATIES, "klantid=4")).isEqualTo(1);
//        //teweede keer dezelfde film kan niet gereserveerd worden.
//        assertThat(repository.createReservatie(reservatie)).isEqualTo(0);
//        assertThat(repository.createReservatie(reservatie)).isEqualTo(0);
//    }


}