package be.vdab.retrovideo.domain;

import be.vdab.retrovideo.sessions.Mandje;

import java.time.LocalDate;

public class Reservatie {
    private final long klantId;
    private final Mandje mandje;
    private final LocalDate reservatie;

    public Reservatie(long klantId, long filmId, Mandje mandje) {
        this.klantId = klantId;
        this.mandje = mandje;
        this.reservatie = LocalDate.now();
    }
}
