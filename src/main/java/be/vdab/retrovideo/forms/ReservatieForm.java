package be.vdab.retrovideo.forms;

import be.vdab.retrovideo.domain.Reservatie;

import javax.validation.constraints.Positive;

public class ReservatieForm extends Reservatie {
    public ReservatieForm(long klantId,@Positive long filmid) {
        super(klantId,
                filmid);
    }

}
