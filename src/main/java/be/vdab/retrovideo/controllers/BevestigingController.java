package be.vdab.retrovideo.controllers;

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
import javax.validation.constraints.Positive;
import java.util.HashSet;

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
    public ModelAndView bevestig(@PathVariable("id")@Positive long id) {
        var modelAndView = new ModelAndView("bevestiging", "mandje", mandje);
        reservatieService.findKlantById(id)
                .ifPresent(klant -> {mandje.setKlantid(klant.getId());
                modelAndView.addObject("klant",klant);
                });
        return modelAndView;
    }

    @GetMapping("gedaan")
    public ModelAndView bevestigenform() {
        if (!mandje.getIdsVanFilms().isEmpty()) {
            var setVanNietGereserveerd = new HashSet<Long>();
            mandje.getIdsVanFilms().stream()
                    .map(id -> new ReservatieForm(mandje.getKlantid(), id))
                    .forEach(reservatieForm -> {
                        try {
                            reservatieService.maakReservatie(reservatieForm);
                        } catch (DuplicateReservatieException e) {
                            setVanNietGereserveerd.add(reservatieForm.getFilmid());
                        }
                    });
            mandje.getIdsVanFilms().retainAll(setVanNietGereserveerd);
            return new ModelAndView("bevestigd", "nietgereserveerdeFilms",
                    filmService.findFilmsByIds(mandje.getIdsVanFilms()));
        } else {
            return new ModelAndView("bevestigd");
        }

    }
}


















































