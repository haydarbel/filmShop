package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;
import java.util.Optional;

public interface ReservatieService {
    Optional<Klant> findKlantById(long id);
    List<Reservatie> createResevaties(List<Reservatie> reservaties);
    List<Klant> findKlantByLetters(String tekst);

}
