package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private long klantid;
    private final Set<Long> idsVanFilms = new LinkedHashSet<>();


    public void setKlantid(long klantid) {
        this.klantid = klantid;
        System.out.println(klantid);
    }

    public long getKlantid() {
        return klantid;
    }

    public void voegDeFilmToe(long id) {
        idsVanFilms.add(id);
    }

    public Set<Long> getIds() {
        return idsVanFilms;
    }

    public List<Long> getIdsList() {
        return new ArrayList<>(idsVanFilms);
    }

    public void verwijderFilms(Long[] ids) {
        Set<Long> gekozenFilmsOmTeVerwijderen = Arrays.stream(ids).collect(Collectors.toSet());
        idsVanFilms.removeAll(gekozenFilmsOmTeVerwijderen);
    }
}
