package be.vdab.retrovideo.domain;

import javax.validation.constraints.PositiveOrZero;

public class Stock {
    @PositiveOrZero
    private final long voorraad;
    @PositiveOrZero
    private final long gereserveerd;

    public Stock(long voorraad, long gereserveerd) {
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
    }

    public long getVoorraad() {
        return voorraad;
    }

    public long getGereserveerd() {
        return gereserveerd;
    }

    @PositiveOrZero
    public long getBeschikebaar() {
        return voorraad - gereserveerd;
    }
}
