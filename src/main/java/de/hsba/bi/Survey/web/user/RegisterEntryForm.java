package de.hsba.bi.Survey.web.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterEntryForm {

    private String username;

    private String password;

    private String passwordRepeat;
}
