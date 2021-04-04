package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
class IndexController {
    private final FilmService filmService;

    IndexController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index", "genres", filmService.findAllGenres())
                .addObject("films", filmService.findAllFilms());
    }

    @GetMapping("films/genre/{id}")
    public ModelAndView genre(@PathVariable long id) {
        return new ModelAndView("index", "genres", filmService.findAllGenres())
                .addObject("filmsMetGenre", filmService.findFilmsByGenreId(id));
    }

    @GetMapping("film/{id}")
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("film");
        filmService.findAllFilms().stream()
                .filter(film -> film.getId() == id)
                .findFirst()
                .ifPresent(film -> modelAndView.addObject("film", film).addObject(
                        "stock", filmService.findStockById(film.getId()).get()
                ));
        return modelAndView;
    }

}
