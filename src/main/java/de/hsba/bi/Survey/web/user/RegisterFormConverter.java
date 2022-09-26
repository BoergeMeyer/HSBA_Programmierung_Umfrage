package de.hsba.bi.Survey.web.user;

import de.hsba.bi.Survey.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterFormConverter {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    User update(User user, RegisterEntryForm form){
        user.setName((form.getUsername()));
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("Member");
        return user;
    }
}
