package de.hsba.bi.Survey.web.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterEntryForm {
    //notNULL and notEMPTY is missing

    private String username;

    private String password;

    private String passwordRepeat;
}
