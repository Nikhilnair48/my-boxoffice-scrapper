package beans;

import javax.persistence.Embeddable;

@Embeddable
public class DailyDataKey implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String movieID;
	private String numDaysInTheater;
	
	public DailyDataKey() {
		numDaysInTheater = movieID = "";
	}

	public DailyDataKey(String movieID, String numDaysInTheater) {
		super();
		this.movieID = movieID;
		this.numDaysInTheater = numDaysInTheater;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getNumDaysInTheater() {
		return numDaysInTheater;
	}

	public void setNumDaysInTheater(String numDaysInTheater) {
		this.numDaysInTheater = numDaysInTheater;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DailyDataKey [movieID=" + movieID + ", numDaysInTheater="
				+ numDaysInTheater + "]";
	}
}
