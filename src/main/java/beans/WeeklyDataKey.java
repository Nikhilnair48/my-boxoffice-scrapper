package beans;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class WeeklyDataKey implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String movieId;
	private String grossToDate;
	
	public WeeklyDataKey() {
		grossToDate = movieId = "";
	}

	public WeeklyDataKey(String movieId, String grossToDate) {
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
		return "WeeklyDataKey [movieId=" + movieId + ", grossToDate="
				+ grossToDate + "]";
	}
	
}
