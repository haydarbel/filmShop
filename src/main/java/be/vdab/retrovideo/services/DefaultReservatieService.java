package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.KlantRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultReservatieService implements ReservatieService{
    private final KlantRepository klantRepository;
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;


    public DefaultReservatieService(KlantRepository klantRepository, ReservatieRepository reservatieRepository,
                                    FilmRepository filmRepository ) {
        this.klantRepository = klantRepository;
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;

    }

    @Override
    public Optional<Klant> findKlantById(long id) {
        return klantRepository.findById(id);
    }


    @Override
    public Set<Long> createResevaties(Set<Reservatie> reservaties) {
        var nietGereserveerdeFilms = new HashSet<Long>();
        for (Reservatie reservatie : reservaties) {
            if(reservatieRepository.createReservatie(reservatie)==1){
                filmRepository.slaDeGereserveerdeFilmsenVerhoogMetEen(reservatie);
            }else {
               nietGereserveerdeFilms.add(reservatie.getFilmid());
            }
        }
            return nietGereserveerdeFilms;
    }


    @Override
    public List<Klant> findKlantByLetters(String tekst) {
        return klantRepository.findByLetters(tekst);
    }




}

