package hu.bme.sch.bss.webcentral.core.model;

public class UserBuilder {
	private User.Builder builder;

	public UserBuilder setBuilder(User.Builder builder) {
		this.builder = builder;
		return this;
	}

	public User createUser() {
		return new User(builder);
	}
}