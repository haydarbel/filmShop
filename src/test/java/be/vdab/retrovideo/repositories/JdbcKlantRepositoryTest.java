package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcKlantRepository.class)
@Sql("/insertKlanten.sql")
class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String KLANTEN = "klanten";
    private final JdbcKlantRepository repository;

    JdbcKlantRepositoryTest(JdbcKlantRepository repository) {
        this.repository = repository;
    }


    @Test
    void findById() {
        assertThat(repository.findById(idVanTestKlant()).get().getFamilienaam())
                .isEqualTo("test1");
    }

    @Test
    void findAllGeeftAlleKlantenGesorteerdOpId() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(KLANTEN))
                .extracting(Klant::getFamilienaam)
                .isSortedAccordingTo(String::compareToIgnoreCase);
    }


    @Test
    void findByLetters() {
        assertThat(repository.findByLetters("ni"))
                .hasSize(countRowsInTableWhere(KLANTEN, "familienaam like '%ni%'"))
                .extracting(Klant::getFamilienaam)
                .allSatisfy(naam -> assertThat(naam.contains("ni")))
                .isSortedAccordingTo(String::compareToIgnoreCase);
    }

    private long idVanTestKlant() {
        return jdbcTemplate.queryForObject("select id from klanten " +
                "where familienaam ='test1'",Long.class);
    }
}