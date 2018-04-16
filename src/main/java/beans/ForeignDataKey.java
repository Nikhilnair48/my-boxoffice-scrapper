package beans;

import javax.persistence.Embeddable;

@Embeddable
public class ForeignDataKey implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String foreignKeyMovieID;
	private String country;
	
	public ForeignDataKey() {
		this.foreignKeyMovieID = "";
		this.country = "";
	}
	
	public ForeignDataKey(String foreignKeyMovieID, String country) {
		this.foreignKeyMovieID = foreignKeyMovieID;
		this.country = country;
	}
	public String getForeignKeyMovieID() {
		return foreignKeyMovieID;
	}
	public void setForeignKeyMovieID(String foreignKeyMovieID) {
		this.foreignKeyMovieID = foreignKeyMovieID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "ForeignDataKey [foreignKeyMovieID=" + foreignKeyMovieID
				+ ", country=" + country + "]";
	}
	
}
