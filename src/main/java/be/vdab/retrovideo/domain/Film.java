package be.vdab.retrovideo.domain;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final long genreId;
    private final String titel;
    private final BigDecimal prijs;

    public Film(long id, long genreId, String titel, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.prijs = prijs;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    @Override
    public String toString() {
        return titel;
    }

}
