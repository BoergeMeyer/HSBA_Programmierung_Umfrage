package de.hsba.bi.Survey.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindByName(){
        User testUser = new User("User","password");
        userRepository.save(testUser);

        final User found = userRepository.findByName("User");

        assertThat(found).isEqualTo(testUser);
    }

}
