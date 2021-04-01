package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findbyId(long id);
    List<Genre> findAll();

}
