package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.exceptions.DuplicateReservatieException;
import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("bevestiging")
class BevestigingController {
    private final ReservatieService reservatieService;
    private final FilmService filmService;
    private final Mandje mandje;

    public BevestigingController(ReservatieService reservatieService, FilmService filmService, Mandje mandje) {
        this.reservatieService = reservatieService;
        this.filmService = filmService;
        this.mandje = mandje;
    }

    @GetMapping("form/{id}")
    public ModelAndView bevestig(@PathVariable long id) {
        var modelAndView = new ModelAndView("bevestiging", "mandje", mandje);
        mandje.setKlantid(id);
        reservatieService.findKlantById(id).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        return modelAndView;
    }

    @GetMapping("gedaan")
    public ModelAndView bevestigenform() {
        var nietGereserveerdeFilms = new HashSet<Long>();
        var deSetVoorReservatie = reservatieSetVanHetMandje();
        for (Reservatie reservatie : deSetVoorReservatie) {
            try {
                reservatieService.maakResevatie(reservatie);
            } catch (DuplicateReservatieException e) {
                nietGereserveerdeFilms.add(reservatie.getFilmid());
            }
        }
        mandje.getIds().retainAll(nietGereserveerdeFilms);
        return new ModelAndView("bevestigd","nietgereserveerdeFilms",
                filmService.findFilmsByIds(mandje.getIds()));
    }


    private Set<Reservatie> reservatieSetVanHetMandje() {
        return mandje.getIds().stream()
                .map(id -> new ReservatieForm(mandje.getKlantid(), id))
                .collect(Collectors.toSet());
    }

}

















