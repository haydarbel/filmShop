package be.vdab.retrovideo.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Genre {
    @Positive
    private final long id;
    @NotBlank
    private final String naam;

    public Genre(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public String toString() {
        return naam;
    }

}
