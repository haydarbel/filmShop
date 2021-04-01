package be.vdab.retrovideo.domain;

import java.time.LocalDate;

public class Reservatie {
    private final long klantId;
    private final long filmId;
    private final LocalDate reservatie;

    public Reservatie(long klantId, long filmId, LocalDate reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }
}
