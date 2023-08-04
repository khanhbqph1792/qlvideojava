package poly.edu.domain;

public class ChangePasswordForm {
	private String userId, password, confirmPassword, currentPassword;

	public ChangePasswordForm() {

	}

	public ChangePasswordForm(String userId, String password, String confirmPassword, String currentPassword) {
		this.userId = userId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.currentPassword = currentPassword;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
