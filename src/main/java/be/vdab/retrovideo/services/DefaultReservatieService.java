package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.KlantRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultReservatieService implements ReservatieService{
    private final KlantRepository klantRepository;
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;

    public DefaultReservatieService(KlantRepository klantRepository, ReservatieRepository reservatieRepository, FilmRepository filmRepository) {
        this.klantRepository = klantRepository;
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Klant> findKlantById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    public void createResevatie(Reservatie reservatie) {
        filmRepository.slaDeGereserveerdeFilmsenVerhoogMetEen(reservatie);
        reservatieRepository.create(reservatie);
    }

    @Override
    public List<Klant> findKlantByLetters(String tekst) {
        return klantRepository.findByLetters(tekst);
    }

}

