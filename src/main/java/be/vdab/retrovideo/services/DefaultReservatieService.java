package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.KlantRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultReservatieService implements ReservatieService{
    private final KlantRepository klantRepository;
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;
    private final Mandje mandje;

    public DefaultReservatieService(KlantRepository klantRepository, ReservatieRepository reservatieRepository,
                                    FilmRepository filmRepository, Mandje mandje) {
        this.klantRepository = klantRepository;
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;
        this.mandje = mandje;
    }

    @Override
    public Optional<Klant> findKlantById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    public List<Reservatie> createResevaties(List<Reservatie> reservaties) {
        var nietGereserveerdeFilms = new ArrayList<Reservatie>();
        for (Reservatie reservatie : reservaties) {
            if(reservatieRepository.create(reservatie)==1){
                filmRepository.slaDeGereserveerdeFilmsenVerhoogMetEen(reservatie);
            }else {
               nietGereserveerdeFilms.add(reservatie);
            }
        }
            return nietGereserveerdeFilms;
    }

    @Override
    public List<Klant> findKlantByLetters(String tekst) {
        return klantRepository.findByLetters(tekst);
    }



    public List<Reservatie> reservatieListVanMandje() {
        var reservatielist = new ArrayList<Reservatie>();
        var klantid = mandje.getKlantid();
        for (long film : mandje.getIdsList()) {
            reservatielist.add(new ReservatieForm(klantid, film));
        }
        return reservatielist;
    }

}

