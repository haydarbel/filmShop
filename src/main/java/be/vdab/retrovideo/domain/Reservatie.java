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
    @Positive
    private long filmid;
    @DateTimeFormat(style = "S-")
    private final LocalDate datum;

    public Reservatie(long klantId, long filmid) {
        this.klantId = klantId;
        this.filmid = filmid;
        this.datum = LocalDate.now();
    }
    public long getKlantId() {
        return klantId;
    }
    public long getFilmid() {
        return filmid;
    }
    public LocalDate getDatum() {
        return datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservatie)) return false;
        Reservatie that = (Reservatie) o;
        return klantId == that.klantId && filmid == that.filmid && datum.equals(that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klantId, filmid, datum);
    }
}
