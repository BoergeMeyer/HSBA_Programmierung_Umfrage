package de.hsba.bi.Survey.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUsername());
    }

    public Integer findUserByName(String username){
        return userRepository.findUserByName(username);
    }

    public String returnNumberOfUsers(){
        return Integer.toString(userRepository.findAll().size());
    }

    public User returnUserByName(String username){
        return userRepository.returnUserByName(username);
    }
}
