package be.vdab.retrovideo.forms;

import be.vdab.retrovideo.domain.Film;

import java.math.BigDecimal;

public class FilmForm extends Film {
    public FilmForm(long id) {
        super(id, 0, "titel", BigDecimal.ZERO);
    }
}
