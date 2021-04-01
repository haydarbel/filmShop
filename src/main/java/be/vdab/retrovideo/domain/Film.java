package be.vdab.retrovideo.domain;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final long genreId;
    private final String titel;
    private final BigDecimal prijs;
    private long voorraad;
    private long gereserveerd;

    public Film(long id, long genreId, String titel, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.prijs = prijs;
    }

    public Film(long id, long genreId, String titel, BigDecimal prijs,
                long voorraad, long gereserveerd) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.prijs = prijs;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
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

    public  long getVoorraad() {
        return voorraad;
    }

    public  long getGereserveerd() {
        return gereserveerd;
    }

    public  void setVoorraad(long voorraad) {
        this.voorraad = voorraad;
    }

    public  void setGereserveerd(long gereserveerd) {
        this.gereserveerd = gereserveerd;
    }
}
