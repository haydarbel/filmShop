package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReservatieService {
    Optional<Klant> findKlantById(long id);
    List<Klant> findAllKlanten();
    List<Klant> findKlantByLetters(String letters);
    void createResevatie(Reservatie reservatie);
    List<Klant> findKlantByLetters(String tekst);

}
