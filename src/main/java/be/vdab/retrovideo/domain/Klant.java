package be.vdab.retrovideo.domain;

public class Klant {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final String straatNummer;
    private final String postcode;
    private final String gemeente;

    public Klant(long id, String voornaam, String familienaam, String straatNummer, String postcode, String gemeente) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getnaam() {
        return voornaam+" "+familienaam;
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
        return voornaam+" "+familienaam;
    }
}
