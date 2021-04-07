package be.vdab.retrovideo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.retrovideo.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Comparator;

@JdbcTest
@Import(JdbcGenreRepository.class)
@Sql("/insertGenres.sql")
class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GENRES = "genres";
    private final JdbcGenreRepository repository;

    JdbcGenreRepositoryTest(JdbcGenreRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAllGeeftAlleGenresGesorteerdOpNaam() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(GENRES))
                .extracting(Genre::getNaam)
                .isSortedAccordingTo(String::compareToIgnoreCase);
    }

}


