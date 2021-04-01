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

    public final Film[] films = {
            new Film(1, 2, "Raiders of the lost ark", BigDecimal.valueOf(3.5)),
            new Film(2, 7, "Harry Potter", BigDecimal.valueOf(3)),
            new Film(3, 11, "Love story", BigDecimal.valueOf(3)),
            new Film(4, 4, "Two moon junction", BigDecimal.valueOf(3.5)),
            new Film(5, 6, "Police academy", BigDecimal.valueOf(3.5)),
            new Film(8,7,"Babe",BigDecimal.valueOf(3))
    };
    public final Genre[] genres = {
            new Genre(4, "Erotiek"),
            new Genre(2, "Avontuur"),
            new Genre(6, "Humor"),
            new Genre(7, "Kinderfilm"),
            new Genre(11, "Sentimenteel")
    };


    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index", "films", filmService.findAllFilms())
                .addObject("genres", filmService.findAllGenres());
    }

    @GetMapping("films/{id}")
    public ModelAndView genre(@PathVariable long id) {
        var modelAndView = new ModelAndView("index","films",filmService.findAllFilms());
        modelAndView.addObject("genres",filmService.findAllGenres());
        modelAndView.addObject("filmsMetGenre", filmService.findFilmsByGenre(id));
        return modelAndView;

    }
    @GetMapping("film/{id}")
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("film");
        filmService.findAllFilms().stream()
                .filter(film -> film.getId() == id)
                .findFirst()
                .ifPresent(film -> modelAndView.addObject("film",film));
        return modelAndView;
    }



/*    private List<Film> filmMetGenre(long id) {
        return Arrays.stream(films)
                .filter(film -> film.getGenreId() == id)
                .collect(Collectors.toList());
    }

    private List<Genre> genresList(Genre[] genres) {
        return Arrays.stream(genres)
                .sorted((genre1,genre2)->genre1.getNaam().compareTo(genre2.getNaam()))
                .collect(Collectors.toList());
    }

    private Film[] VoegStatics(Film[] films) {
        for (Film film : films) {
            film.setVoorraad(3);
            film.setGereserveerd(0);
        }
        return films;
    }*/

}
