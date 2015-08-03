package demo;

import demo.model.User;
import demo.repo.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PostReady implements CommandLineRunner {

    @Autowired(required = false)
    private InMemoryUsersConnectionRepository repo;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            repo.setConnectionSignUp(connection -> {
                UserProfile profile = connection.fetchUserProfile();
                User user = new User(profile.getEmail(),
                        profile.getFirstName(), profile.getLastName(), profile.getEmail().endsWith("prasenjit.net"));
                userRepository.save(user);
                return profile.getEmail();
            });
        } catch (NullPointerException e) {
            log.error("Failed to aquire UserConnectionRepository");
        }
    }
}
