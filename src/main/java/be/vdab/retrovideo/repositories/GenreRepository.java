package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAll();

}
