package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantRepository {
    Optional<Klant> findById(long id);
    List<Klant> findAll();
    List<Klant> findByLetters(String tekst);
}
