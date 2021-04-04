package be.vdab.retrovideo.forms;

import be.vdab.retrovideo.domain.Reservatie;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class ReservatieForm extends Reservatie {
    public ReservatieForm(long klantId, List<Long> filmids) {
        super(klantId, filmids, LocalDate.now());
    }

}
