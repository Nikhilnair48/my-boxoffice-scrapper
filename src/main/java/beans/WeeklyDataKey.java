package beans;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class WeeklyDataKey {
	
	private String movieId;
	private Long weekStart;
	
	public WeeklyDataKey() {
		movieId = "";
	}
	
	public WeeklyDataKey(String movieId, Long weekStart) {
		super();
		this.movieId = movieId;
		this.weekStart = weekStart;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Long getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(Long weekStart) {
		this.weekStart = weekStart;
	}
	
	@Override
	public String toString() {
		return "WeeklyDataKey [movieId=" + movieId + ", weekStart=" + weekStart
				+ "]";
	}
	
}
