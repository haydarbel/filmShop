package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.PositiveOrZero;

@Component
@SessionScope
public class Identificatie {
    private static final long serialVersionUID = 1L;
    @PositiveOrZero
    private long klantId;

    public long getKlantId() {
        return klantId;
    }

    public void setKlantId(long klantId) {
        this.klantId = klantId;
    }
}