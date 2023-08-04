package poly.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Favorites database table.
 * 
 */
@Entity
@Table(name="Favorites",uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId","VideoId"})})
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long favoriteId;

	@Column(name="LikeDate")
	private Date likeDate;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	@ManyToOne
	@JoinColumn(name="VideoId")
	private Video video;

	public Favorite() {
	}

	

	public long getFavoriteId() {
		return favoriteId;
	}



	public void setFavoriteId(long favoriteId) {
		this.favoriteId = favoriteId;
	}



	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
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