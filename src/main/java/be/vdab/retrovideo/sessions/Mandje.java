package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Long> idsVanFilms = new LinkedHashSet<>();

    public void voegToe(long id) {
        idsVanFilms.add(id);
    }

    public Set<Long> getIds() {
        return idsVanFilms;
    }

    public void verwijderFilms(Long[] ids) {
        Set<Long> gekozenFilmsOmTeVerwijderen = Arrays.stream(ids).collect(Collectors.toSet());
        idsVanFilms.removeAll(gekozenFilmsOmTeVerwijderen);
    }
}
