package be.vdab.retrovideo.repositories;
import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.retrovideo.domain.Film;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@JdbcTest
@Import(JdbcFilmRepository.class)
@Sql("/insertFilms.sql")
class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String FILMS = "films";
    private final JdbcFilmRepository repository;

    JdbcFilmRepositoryTest(JdbcFilmRepository repository) {
        this.repository = repository;
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestFilm()).get().getTitel()).isEqualTo("test1");
        assertThat(repository.findById(idVanTest2Film()).get().getTitel()).isEqualTo("test2");
    }

    @Test
    void findAllGeeftAlleFilmsGesorteerdOpId() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(FILMS))
                .extracting(Film::getId).isSorted();
    }

    private long idVanTestFilm() {
        return jdbcTemplate.queryForObject(
                "select id from films where titel='test1'", Long.class);
    }

    private long idVanTest2Film() {
        return jdbcTemplate.queryForObject(
                "select id from films where titel='test2'", Long.class);
    }


    @Test
    void findByGenreId() {
        assertThat(repository.findByGenreId(1))
                .hasSize(countRowsInTableWhere(FILMS,"genreId=1"));
        assertThat(repository.findByGenreId(10))
                .hasSize(countRowsInTableWhere(FILMS,"genreId=10"));

    }


}