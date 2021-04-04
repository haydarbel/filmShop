package be.vdab.retrovideo.domain;

public class Stock {
    private final long voorraad;
    private long gereserveerd;

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



    public void setGereserveerd(long gereserveerd) {
        this.gereserveerd = gereserveerd;
    }

    public long getBeschikebaar() {
        return voorraad - gereserveerd;
    }
}
