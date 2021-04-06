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
    private long filmid;
    @DateTimeFormat(style = "S-")
    private final LocalDate datum;

    public Reservatie(long klantId,List<Long> filmids,@NotNull LocalDate datum) {
        this.klantId = klantId;
        this.filmid = filmid;
        this.datum = datum;
    }

    public Reservatie(long klantId, long filmid) {
        this.klantId = klantId;
        this.filmid = filmid;
        this.datum = LocalDate.now();
    }

    public Reservatie(long klantId, @NotNull LocalDate datum) {
        this.klantId = klantId;
        this.datum = datum;
    }

    public Reservatie(long id, long klantId, LocalDate now) {
        this.klantId = klantId;
        this.datum = LocalDate.now();
    }

    public long getKlantId() {
        return klantId;
    }
    public Long getFilmid() {
        return filmid;
    }
    public LocalDate getDatum() {
        return datum;
    }



}
