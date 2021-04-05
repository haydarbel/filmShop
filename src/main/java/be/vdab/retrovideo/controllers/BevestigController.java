package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ReservatieForm;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("bevestigen")
public class BevestigController {
    private final ReservatieService reservatieService;
    private final Mandje mandje;

    public BevestigController(ReservatieService reservatieService,  Mandje mandje) {
        this.reservatieService = reservatieService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public String identificatie(@PathVariable long id) {
        mandje.setKlantid(id);
        return "redirect:/bevestigen/form";
    }

    @GetMapping("form")
    public ModelAndView bevestig() {
        var modelAndView = new ModelAndView("bevestigen","mandje",mandje);
        reservatieService.findKlantById(mandje.getKlantid()).ifPresent(
                klant -> modelAndView.addObject("klant", klant));
        return modelAndView;
    }

    @GetMapping("gedaan")
    public String bevestigenform() {
        var reservatie = new ReservatieForm(mandje.getKlantid(), mandje.getIdsList());
        reservatieService.createResevatie(reservatie);
        mandje.resetMandje();
        return "redirect:/bevestigen/bevestigd";
    }

    @GetMapping("bevestigd")
    public ModelAndView bevestigd() {
        return new ModelAndView("bevestigd");
    }

}
