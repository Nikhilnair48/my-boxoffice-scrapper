package beans;

import javax.persistence.Embeddable;

@Embeddable
public class DailyDataKey {
	
	private String movieID;
	private String date;		// STORE THE DATE AS TIME FROM 1970 (LONG)
	
	public DailyDataKey() {
		movieID = "";
	}
	
	public DailyDataKey(String movieID, String date) {
		super();
		this.movieID = movieID;
		this.date = date;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DailyDataKey [movieID=" + movieID + ", Date=" + date + "]";
	}

}
