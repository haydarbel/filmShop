package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ZoekForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klant")
class KlantControllers {
    private final FilmService filmService;
    private final ReservatieService reservatieService;
    private final Mandje mandje;


    KlantControllers(FilmService filmService, ReservatieService reservatieService, Mandje mandje) {
        this.filmService = filmService;
        this.reservatieService = reservatieService;
        this.mandje = mandje;

    }


    @GetMapping("form")
    public ModelAndView klant() {
        return new ModelAndView("klant")
                .addObject(new ZoekForm("letters hier"));
    }

    @GetMapping()
    public ModelAndView beginNaam(@Valid ZoekForm form, Errors error) {
        var modelAndView = new ModelAndView("klant");
        if (error.hasErrors()) {
            return modelAndView;
        }
        return new ModelAndView("klant", "klanten",
                reservatieService.fintKlantByLetters(form.getLetters()));
    }



}
