package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    @PositiveOrZero
    private long klantid;
    @NotEmpty
    private final Set<Long> idsVanFilms = new HashSet<>();

    public void setKlantid(long klantid) {
        this.klantid = klantid;
    }

    public long getKlantid() {
        return klantid;
    }

    public void voegDeFilmToe(long id) {
        idsVanFilms.add(id);
    }

    public Set<Long> getIdsVanFilms() {
        return idsVanFilms;
    }

    public void verwijderFilms(Long[] ids) {
        Set<Long> gekozenFilmsOmTeVerwijderen = Arrays.stream(ids).collect(Collectors.toSet());
        idsVanFilms.removeAll(gekozenFilmsOmTeVerwijderen);
    }
}
