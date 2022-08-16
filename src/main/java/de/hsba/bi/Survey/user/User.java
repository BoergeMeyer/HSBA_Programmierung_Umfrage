package de.hsba.bi.Survey.user;

import javax.persistence.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    public static String ADMIN_ROLE = "ADMIN";
    public static String MEMBER_ROLE = "MEMBER";

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private String role;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
