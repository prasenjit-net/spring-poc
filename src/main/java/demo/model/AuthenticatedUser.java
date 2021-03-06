package demo.model;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.security.SocialUser;

public class AuthenticatedUser extends SocialUser {
	private static final long serialVersionUID = 117727369625366010L;

	private String fullName;

	public AuthenticatedUser(String username, String fullName, boolean admin) {
		super(username, username, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}
}
