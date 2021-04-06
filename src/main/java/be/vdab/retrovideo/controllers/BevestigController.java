package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("bevestigen")
class BevestigController {
    private final ReservatieService reservatieService;
    private final FilmService filmService;
    private final Mandje mandje;

    public BevestigController(ReservatieService reservatieService, FilmService filmService, Mandje mandje) {
        this.reservatieService = reservatieService;
        this.filmService = filmService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public String identificatie(@PathVariable long id) {
        mandje.setKlantid(id);
        return "redirect:/bevestigen/form";
    }

    @GetMapping("form")
    public ModelAndView bevestig() {
        var modelAndView = new ModelAndView("bevestigen", "mandje", mandje);
        reservatieService.findKlantById(mandje.getKlantid()).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        return modelAndView;
    }

    @GetMapping("gedaan")
    public ModelAndView bevestigenform() {
        var nietgereserveerdeFilms =
                reservatieService.createResevaties(reservatieSetVanMandje());
        mandje.getIds().retainAll(nietgereserveerdeFilms);
        return new ModelAndView("bevestigd", "nietgereserveerdeFilms",
                filmService.findFilmsByIds(nietgereserveerdeFilms));
    }

    @GetMapping("bevestigd")
    public ModelAndView bevestigd() {
        return new ModelAndView("bevestigd");
    }


    public Set<Reservatie> reservatieSetVanMandje() {
        var reservatieSet = new HashSet<Reservatie>();
        for (long film : mandje.getIds()) {
            reservatieSet.add(new ReservatieForm(mandje.getKlantid(), film));
        }
        return reservatieSet;
    }
}
