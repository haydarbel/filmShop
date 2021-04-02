package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ZoekForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klant")
class KlantControllers {
    private final FilmService filmService;
    private final KlantService klantService;
    private final Mandje mandje;

    KlantControllers(FilmService filmService, KlantService klantService, Mandje mandje) {
        this.filmService = filmService;
        this.klantService = klantService;
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
                klantService.fintKlantByLetters(form.getLetters()));
    }

    @GetMapping("bevestigen")
    public ModelAndView bevestigen() {
        var modelAndView = new ModelAndView("bevestigen","films",
                filmService.findFilmsByIds(mandje.getIds()));
        return modelAndView;
    }

}
