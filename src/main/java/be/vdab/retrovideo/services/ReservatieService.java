package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReservatieService {
    Optional<Klant> findKlantById(long id);
    List<Klant> findKlantByLetters(String tekst);
    boolean maakReservatie(Reservatie reservatie);

}
