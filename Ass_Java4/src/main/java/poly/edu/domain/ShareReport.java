package poly.edu.domain;

import java.util.Date;

public class ShareReport {
	private String fullname, email, emails;
	private Date shareDate;

	public ShareReport() {
	}

	public ShareReport(String fullname, String email, String emails, Date shareDate) {
		this.fullname = fullname;
		this.email = email;
		this.emails = emails;
		this.shareDate = shareDate;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
}
