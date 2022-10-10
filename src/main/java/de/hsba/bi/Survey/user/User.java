package de.hsba.bi.Survey.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private String role;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(User user){
        name = user.getName();
        password = user.getPassword();
    }

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }
}
