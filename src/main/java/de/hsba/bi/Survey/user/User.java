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
    public static String ADMIN_ROLE = "ADMIN";
    public static String MEMBER_ROLE = "MEMBER";

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    public static Long getCurrentUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
            return ((User) principal).getId();
        }
        return null;
    }

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Getter
    @Setter
    @Basic(optional = false)
    private String password;

    @Getter
    @Setter
    private String role;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(User user){
        name = user.getName();
        password = user.getPassword();
    }
}
