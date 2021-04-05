package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
 class MyControllerAdvice {
    private final Mandje mandje;

    public MyControllerAdvice(Mandje mandje) {
        this.mandje = mandje;
    }

    @ModelAttribute
    void exraDataToevoegenAanModel(Model model) {
        model.addAttribute(mandje);
    }
}
