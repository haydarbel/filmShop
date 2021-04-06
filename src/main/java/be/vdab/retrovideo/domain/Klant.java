package be.vdab.retrovideo.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class Klant {
    @Positive
    private final long id;
    @NotBlank
    private final String familienaam;
    @NotBlank
    private final String voornaam;
    @NotBlank
    private final String straatNummer;
    @NotBlank
    private final String postcode;
    @NotBlank
    private final String gemeente;

    public Klant(long id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
        this.id = id;
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getnaam() {
        return voornaam + " " + familienaam;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getStraatNummer() {
        return straatNummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    @Override
    public String toString() {
        return voornaam + " " + familienaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klant)) return false;
        Klant klant = (Klant) o;
        return id == klant.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
