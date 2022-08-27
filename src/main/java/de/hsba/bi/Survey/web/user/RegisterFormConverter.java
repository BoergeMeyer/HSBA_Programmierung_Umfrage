package de.hsba.bi.Survey.web.user;

import de.hsba.bi.Survey.user.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterFormConverter {

    User update(User user, RegisterEntryForm form){
        user.setName((form.getUsername()));
        user.setPassword((form.getPassword()));
        user.setRole("Member");
        return user;
    }
}
