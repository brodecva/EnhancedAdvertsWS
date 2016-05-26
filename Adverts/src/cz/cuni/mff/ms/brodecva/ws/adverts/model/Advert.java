/**
 * 
 */
package cz.cuni.mff.ms.brodecva.ws.adverts.model;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author user
 *
 */
@XmlRootElement(name = "Advert")
public class Advert {

	private long id;
	
	private String text;
	
	private Date published;
	
	private String email;
	
	private String phone;
	
	private Set<String> tags;
	
	private double latitude;
	
	private double longitude;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ (this.id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Advert)) {
			return false;
		}
		Advert other = (Advert) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Advert [id=" + id + ", text=" + text + ", published="
				+ published + ", email=" + email + ", phone=" + phone
				+ ", tags=" + tags + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
}
