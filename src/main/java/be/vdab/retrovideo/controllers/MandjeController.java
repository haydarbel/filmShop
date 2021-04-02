package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("mandje")
 class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @PostMapping("{id}")
    public String voegToe(@PathVariable long id) {
        mandje.voegToe(id);
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandje() {
        return new ModelAndView("mandje",
                "films", filmService.findFilmsByIds(mandje.getIds()))
                .addObject("totalePrijs",filmService.findTotalePrijsByIds(mandje.getIds()));
    }

    @PostMapping("verwijderen")
    public String verwijderFilms(Optional<Long[]> id) {
        id.ifPresent(mandje::verwijderFilms);
        return "redirect:/mandje";
    }
}
