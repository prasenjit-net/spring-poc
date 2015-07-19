package demo.model;

import java.util.Collections;

import org.springframework.social.security.SocialUser;

public class AuthenticatedUser extends SocialUser {
	private static final long serialVersionUID = 117727369625366010L;

	private String fullName;

	public AuthenticatedUser(String username, String fullName) {
		super(username, username, Collections.emptyList());
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}
}
