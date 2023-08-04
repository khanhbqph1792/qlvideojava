package poly.edu.domain;

public class LoginForm {
	private String userId, password;
	private boolean remember;

	public LoginForm() {
	}

	public LoginForm(String userId, String password, boolean remember) {
		this.userId = userId;
		this.password = password;
		this.remember = remember;
	}

	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
}
