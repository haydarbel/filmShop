package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Reservatie {
    @Positive
    private final long klantId;
    private List<Long> filmids;
    @NotNull
    @DateTimeFormat(style = "S-")
    private final LocalDate datum;

    public Reservatie(long klantId,List<Long> filmids,@NotNull LocalDate datum) {
        this.klantId = klantId;
        this.filmids = filmids;
        this.datum = datum;
    }

    public Reservatie(long klantId, List<Long> filmids) {
        this.klantId = klantId;
        this.filmids = filmids;
        this.datum = LocalDate.now();
    }

    public Reservatie(long klantId, @NotNull LocalDate datum) {
        this.klantId = klantId;
        this.datum = datum;
    }

    public Reservatie(long klantId) {
        this.klantId = klantId;
        this.datum = LocalDate.now();
    }

    public long getKlantId() {
        return klantId;
    }

    public void addFilm(long id) {
        filmids.add(id);
    }

    public void setFilmids(List<Long> filmids) {
        this.filmids = filmids;
    }

    public List<Long> getFilmids() {
        return filmids;
    }
    public LocalDate getDatum() {
        return datum;
    }
}
