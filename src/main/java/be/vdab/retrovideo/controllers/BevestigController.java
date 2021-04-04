package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Identificatie;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
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
        return "redirect:/bevestigen/form";
    }

    @GetMapping("form")
    public ModelAndView bevestig() {
        var modelAndView = new ModelAndView("bevestigen")
                .addObject(new ReservatieForm(0,List.of()));
        modelAndView.addObject("films", filmService.findFilmsByIds(
                mandje.getIds()));
        reservatieService.findKlantById(identificatie.getKlantId()).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        modelAndView.addObject("mandje", mandje);
        return modelAndView;
    }





    @PostMapping("bevestigd/ok")
    public ModelAndView bevestigenform(@Valid ReservatieForm form, Errors errors) {
        if (errors.hasErrors()) {
            var modelAndView = new ModelAndView("bevestigen").
                    addObject("films", filmService.findFilmsByIds(
                            mandje.getIds()));
            reservatieService.findKlantById(identificatie.getKlantId()).ifPresent(
                    klant -> modelAndView.addObject("klant", klant));
            modelAndView.addObject("mandje", mandje);
            return modelAndView;
        }
        reservatieService.createResevatie(form);
        return  new ModelAndView("redirect:/bevestigen/bevestigd");
    }


    @GetMapping("bevestigd")
    public ModelAndView bevestigd() {
        reservatieService.createResevatie(new ReservatieForm(mandje.getKlantid(),
                mandje.getIdsList()));
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
