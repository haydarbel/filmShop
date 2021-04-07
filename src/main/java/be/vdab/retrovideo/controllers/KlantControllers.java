package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ZoekForm;
import be.vdab.retrovideo.services.ReservatieService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klant")
class KlantControllers {
    private final ReservatieService reservatieService;

    KlantControllers(ReservatieService reservatieService) {
        this.reservatieService = reservatieService;
    }

    @GetMapping("byletters/form")
    public ModelAndView klant() {
        return new ModelAndView("klant")
                .addObject(new ZoekForm(""));
    }

    @GetMapping("byletters")
    public ModelAndView beginNaam(@Valid ZoekForm form, Errors error) {
        if (error.hasErrors()) {
            return new ModelAndView("klant");
        }
        return new ModelAndView("klant", "klanten",
                reservatieService.findKlantByLetters(form.getLetters()));
    }
}
