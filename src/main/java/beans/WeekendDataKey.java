package beans;

import javax.persistence.Embeddable;

@Embeddable
public class WeekendDataKey implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String movieId;
	private String grossToDate;
	
	public WeekendDataKey() {
		movieId = "";
	}
	
	public WeekendDataKey(String movieId, String grossToDate) {
		super();
		this.movieId = movieId;
		this.grossToDate = grossToDate;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getGrossToDate() {
		return grossToDate;
	}

	public void setGrossToDate(String grossToDate) {
		this.grossToDate = grossToDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WeekendDataKey [movieId=" + movieId + ", grossToDate="
				+ grossToDate + "]";
	}

}
