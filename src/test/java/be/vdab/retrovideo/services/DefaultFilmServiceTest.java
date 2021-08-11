package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.JdbcFilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@JdbcTest
@Import(JdbcFilmRepository.class)
@Sql("/insertFilms.sql")
class DefaultFilmServiceTest {
    private final DefaultFilmService service;
    private final FilmRepository repository;

    public DefaultFilmServiceTest(DefaultFilmService service, FilmRepository repository) {
        this.service = service;
        this.repository = repository;
    }



    @Test
    void findFilmById() {
        assertThat(service.findFilmById(1).get().getTitel()).isEqualTo("test1");
    }
}