package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.exceptions.ReservatieException;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.KlantRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DefaultReservatieService implements ReservatieService {
    private final KlantRepository klantRepository;
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;


    public DefaultReservatieService(KlantRepository klantRepository, ReservatieRepository reservatieRepository,
                                    FilmRepository filmRepository) {
        this.klantRepository = klantRepository;
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;

    }

    @Override
    public Optional<Klant> findKlantById(long id) {
        return klantRepository.findById(id);
    }


//    @Override
//    public Set<Long> createReservaties(Set<Reservatie> reservaties) {
//        var nietGereserveerdeFilms = new HashSet<Long>();
//        reservaties.forEach(reservatie -> {
//            if(reservatieRepository.createReservatie(reservatie)==1){
//                filmRepository.slaDeGereserveerdeFilmsenVerhoogMetEen(reservatie);
//            }else {
//                nietGereserveerdeFilms.add(reservatie.getFilmid());
//            }});
//        return nietGereserveerdeFilms;
//    }


    @Override
    public List<Klant> findKlantByLetters(String tekst) {
        return klantRepository.findByLetters(tekst);
    }

    @Override
    @Transactional
    public boolean maakResevatie(Reservatie reservatie) {
        filmRepository.slaDeGereserveerdeFilmsenVerhoogMetEen(reservatie);
        if (!reservatieRepository.createReservatie(reservatie)){
            throw new ReservatieException();
        }
        return true;
    }
}

