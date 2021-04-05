package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Identificatie;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("bevestigen")
public class BevestigController {
    private final ReservatieService reservatieService;
    private final FilmService filmService;
    private final Mandje mandje;
    private final Identificatie identificatie;

    public BevestigController(ReservatieService reservatieService, FilmService filmService, Mandje mandje, Identificatie identificatie) {
        this.reservatieService = reservatieService;
        this.filmService = filmService;
        this.mandje = mandje;
        this.identificatie = identificatie;
    }

    @PostMapping("{id}")
    public String identificatie(@PathVariable long id) {
        identificatie.setKlantId(id);
        mandje.setKlantid(identificatie.getKlantId());
        return "redirect:/bevestigen/form";
    }

    @GetMapping("form")
    public ModelAndView bevestig() {
        var modelAndView = new ModelAndView("bevestigen")
                .addObject(new ReservatieForm(0, List.of()));
        modelAndView.addObject("films", filmService.findFilmsByIds(
                mandje.getIds()));
        reservatieService.findKlantById(identificatie.getKlantId()).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        modelAndView.addObject("mandje", mandje);
        return modelAndView;
    }


    private final ReservatieForm reservatieDene = new ReservatieForm(3,
            List.of(5L, 6L));


    @PostMapping("gedaan")
    public ModelAndView bevestigenform() {
        System.out.println(mandje.getKlantid());
        var reservatie = new ReservatieForm(mandje.getKlantid(), mandje.getIdsList());
        reservatieService.createResevatie(reservatie);
        mandje.resetMandje();
        return new ModelAndView("redirect:/bevestigen/bevestigd");
    }


    @GetMapping("bevestigd")
    public ModelAndView bevestigd() {
        return new ModelAndView("bevestigd");
    }

    @GetMapping
    public ModelAndView bevestigen() {
        var modelAndView = new ModelAndView("bevestigen").
                addObject("films", filmService.findFilmsByIds(
                        mandje.getIds()));
        reservatieService.findKlantById(identificatie.getKlantId()).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        modelAndView.addObject("mandje", mandje);
        return modelAndView;
    }

}
