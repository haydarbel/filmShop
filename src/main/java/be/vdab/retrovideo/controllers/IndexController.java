package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.exceptions.FilmNietGevondenException;
import be.vdab.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Positive;

@Controller
@RequestMapping("/")
@Validated
class IndexController {
    private final FilmService filmService;

    IndexController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index", "genres", filmService.findAllGenres());
    }

    @GetMapping("films/genre/{id}")
    public ModelAndView genre(@PathVariable("id")@Positive long id) {
        return new ModelAndView("index", "genres", filmService.findAllGenres())
                .addObject("filmsMetGenre", filmService.findFilmsByGenreId(id));
    }

    @GetMapping("film/{id}")
    public ModelAndView film(@PathVariable("id")@Positive  long id ) {
       var modelAndView = new ModelAndView("film");
        try {
            filmService.findFilmById(id)
                    .ifPresent(film ->modelAndView.addObject("film",film)
                   .addObject("stock",filmService.findStockById(id).get()));
        } catch (FilmNietGevondenException e) {
            return modelAndView;
        }
        return modelAndView;
    }

}
