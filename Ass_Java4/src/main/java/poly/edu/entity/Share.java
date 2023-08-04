package poly.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Shares database table.
 * 
 */
@Entity
@Table(name = "Shares", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId", "VideoId" }) })
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shareId;

	@Column(name = "Emails")
	private String emails;

	@Column(name = "ShareDate")
	private Date shareDate;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Share() {
	}

	public String getEmails() {
		return emails;
	}

	public long getShareId() {
		return shareId;
	}

	public void setShareId(long shareId) {
		this.shareId = shareId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}