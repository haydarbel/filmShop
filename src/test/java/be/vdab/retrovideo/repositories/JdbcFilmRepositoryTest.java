package be.vdab.retrovideo.repositories;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.ReservatieForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    void findFilmsById() {
        assertThat(repository.findFilmById(idVanTestFilm()).get().getTitel()).isEqualTo("test1");
        assertThat(repository.findFilmById(idVanTest2Film()).get().getTitel()).isEqualTo("test2");
    }

//    @Test
//    void findAllFilmsSortedById() {
//        assertThat(repository.findAllFilms())
//                .hasSize(countRowsInTable(FILMS))
//                .extracting(Film::getId).isSorted();
//    }

    @Test
    void findFilmsByGenreId() {
        assertThat(repository.findFilmsByGenreId(1))
                .hasSize(countRowsInTableWhere(FILMS,"genreId=1"));
        assertThat(repository.findFilmsByGenreId(10))
                .hasSize(countRowsInTableWhere(FILMS,"genreId=10"));

    }

    @Test
    void findFilmsByIds() {
        var id1 = idVanTestFilm();
        var id2=idVanTest2Film();
        assertThat(repository.findFilmsByIds(Set.of(id1,id2)))
                .extracting(Film::getId)
                .containsOnly(id1,id2)
                .isSorted();
    }

    @Test
    void findTotalePrijsByIds() {
        var id1 = idVanTestFilm();
        var id2=idVanTest2Film();
        assertThat(repository.findTotalePrijsByIds(Set.of(id1, id2)))
                .isEqualTo(totalePrijsVanTweeTestEntry());
    }

    @Test
    void findStockById() {
        assertThat(repository.findStockById(idVanTest2Film()).get().getGereserveerd())
                .isEqualTo(gereserveerdVanTest2Film());
    }

//    @Test
//    void slaDeGereserveerdeFilmsenVerhoogMetEen() {
//        var eerstGereserveerdWaarde = gereserveerdVanTest2Film();
//        repository.slaDeGereserveerdeFilmsenVerhoogMetEen(new ReservatieForm(1,List.of(idVanTest2Film())));
//        assertThat(countRowsInTableWhere(FILMS,"titel='test2'" +
//                "and gereserveerd="+(eerstGereserveerdWaarde+1L))).isOne();
//    }


    private long idVanTestFilm() {
        return jdbcTemplate.queryForObject(
                "select id from films where titel='test1'", Long.class);
    }

    private long idVanTest2Film() {
        return jdbcTemplate.queryForObject(
                "select id from films where titel='test2'", Long.class);
    }
    private long gereserveerdVanTest2Film() {
        return jdbcTemplate.queryForObject(
                "select gereserveerd from films where titel='test2'", Long.class);
    }

    private BigDecimal totalePrijsVanTweeTestEntry() {
        return jdbcTemplate.queryForObject(
                "select sum(prijs) from films where titel in ('test1','test2')"
                ,BigDecimal.class);
    }
}
