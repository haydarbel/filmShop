package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Positive;
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
    @GetMapping
    public ModelAndView toonMandje() {
        return new ModelAndView("mandje",
                "films", filmService.findFilmsByIds(mandje.getIdsVanFilms()))
                .addObject("totalePrijs",filmService.findTotalePrijsByIds(mandje.getIdsVanFilms()));
    }

    @PostMapping("{id}")
    public String voegToe(@PathVariable("id") @Positive long id) {
        filmService.findFilmById(id)
                .ifPresent(film->mandje.voegDeFilmToe(film.getId()));
        return "redirect:/mandje";
    }

    @PostMapping("verwijderen")
    public String verwijderFilms(Optional<Long[]> id) {
        id.ifPresent(mandje::verwijderFilms);
        return "redirect:/mandje";
    }
}
