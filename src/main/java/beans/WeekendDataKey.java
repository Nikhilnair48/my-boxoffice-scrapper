package beans;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class WeekendDataKey {

	private String movieId;
	private Long weekendStart;
	
	public WeekendDataKey() {
		movieId = "";
	}
	
	public WeekendDataKey(String movieId, Long weekendStart) {
		super();
		this.movieId = movieId;
		this.weekendStart = weekendStart;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Long getWeekendStart() {
		return weekendStart;
	}

	public void setWeekendStart(Long weekendStart) {
		this.weekendStart = weekendStart;
	}

	@Override
	public String toString() {
		return "WeekendDataKey [movieId=" + movieId + ", weekendStart="
				+ weekendStart + "]";
	}
	
}
