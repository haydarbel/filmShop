package be.vdab.retrovideo.forms;

import javax.validation.constraints.NotBlank;

public class ZoekForm {

    @NotBlank
    private final String letters;

    public ZoekForm(String letters) {
        this.letters = letters;
    }

    public String getLetters() {
        return letters;
    }
}
