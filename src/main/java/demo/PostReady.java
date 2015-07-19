package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.stereotype.Component;

import demo.model.User;
import demo.repo.UserRepository;

@Component
public class PostReady implements CommandLineRunner {

	@Autowired
	private InMemoryUsersConnectionRepository repo;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		repo.setConnectionSignUp(connection -> {
			UserProfile profile = connection.fetchUserProfile();
			User user = new User(profile.getEmail(), profile.getFirstName(),
					profile.getLastName());
			userRepository.save(user);
			return profile.getEmail();
		});
	}
}
