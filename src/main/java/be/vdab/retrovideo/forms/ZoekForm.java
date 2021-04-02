package be.vdab.retrovideo.forms;

import javax.validation.constraints.NotBlank;

public class ZoekForm {

    @NotBlank
    private final String letters;

    public ZoekForm(@NotBlank String letters) {
        this.letters = letters;
    }

    public String getLetters() {
        return letters;
    }
}
