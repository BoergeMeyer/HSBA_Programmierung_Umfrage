package de.hsba.bi.Survey.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    @Query("SELECT Count(u.id) from User u where u.name = :username")
    Integer findUserByName(@Param("username")String username);

    @Query("SELECT u FROM User u WHERE u.name = :username")
    User returnUserByName(@Param("username") String username);
}
