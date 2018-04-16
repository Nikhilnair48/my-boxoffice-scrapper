package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeeklyData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private WeeklyDataKey weekDataKey;
	//@Id
	//private String movieId;
	private Long weekStart;
	private Long weekEnd;
	private String weekRank;
	private String weekGross;
	private String percentChangeFromFromPrevWeek;
	private String numTheatersForWeek;
	private String percentChangeInTheatersFromPrevWeek;
	private String perTheaterAvgForWeek;
	//private String grossToDate;
	private String weeksInRelease;
	
	public WeeklyData() {
		//this.weekDataKey = new WeeklyDataKey();
		//this.movieId = "";
		this.weekRank = "";
		this.weekGross = "";
		this.percentChangeFromFromPrevWeek = "";
		this.numTheatersForWeek = "";
		this.percentChangeInTheatersFromPrevWeek = "";
		this.perTheaterAvgForWeek = "";
		//this.grossToDate = "";
		this.weeksInRelease = "";
	}

	public WeeklyData(WeeklyDataKey weekDataKey, Long weekStart, Long weekEnd,
			String weekRank, String weekGross,
			String percentChangeFromFromPrevWeek, String numTheatersForWeek,
			String percentChangeInTheatersFromPrevWeek,
			String perTheaterAvgForWeek, String weeksInRelease) {
		super();
		this.weekDataKey = weekDataKey;
		this.weekStart = weekStart;
		this.weekEnd = weekEnd;
		this.weekRank = weekRank;
		this.weekGross = weekGross;
		this.percentChangeFromFromPrevWeek = percentChangeFromFromPrevWeek;
		this.numTheatersForWeek = numTheatersForWeek;
		this.percentChangeInTheatersFromPrevWeek = percentChangeInTheatersFromPrevWeek;
		this.perTheaterAvgForWeek = perTheaterAvgForWeek;
		this.weeksInRelease = weeksInRelease;
	}

	public WeeklyDataKey getWeekDataKey() {
		return weekDataKey;
	}

	public void setWeekDataKey(WeeklyDataKey weekDataKey) {
		this.weekDataKey = weekDataKey;
	}

	public Long getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Long weekStart) {
		this.weekStart = weekStart;
	}

	public Long getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(Long weekEnd) {
		this.weekEnd = weekEnd;
	}

	public String getWeekRank() {
		return weekRank;
	}

	public void setWeekRank(String weekRank) {
		this.weekRank = weekRank;
	}

	public String getWeekGross() {
		return weekGross;
	}

	public void setWeekGross(String weekGross) {
		this.weekGross = weekGross;
	}

	public String getPercentChangeFromFromPrevWeek() {
		return percentChangeFromFromPrevWeek;
	}

	public void setPercentChangeFromFromPrevWeek(
			String percentChangeFromFromPrevWeek) {
		this.percentChangeFromFromPrevWeek = percentChangeFromFromPrevWeek;
	}

	public String getNumTheatersForWeek() {
		return numTheatersForWeek;
	}

	public void setNumTheatersForWeek(String numTheatersForWeek) {
		this.numTheatersForWeek = numTheatersForWeek;
	}

	public String getPercentChangeInTheatersFromPrevWeek() {
		return percentChangeInTheatersFromPrevWeek;
	}

	public void setPercentChangeInTheatersFromPrevWeek(
			String percentChangeInTheatersFromPrevWeek) {
		this.percentChangeInTheatersFromPrevWeek = percentChangeInTheatersFromPrevWeek;
	}

	public String getPerTheaterAvgForWeek() {
		return perTheaterAvgForWeek;
	}

	public void setPerTheaterAvgForWeek(String perTheaterAvgForWeek) {
		this.perTheaterAvgForWeek = perTheaterAvgForWeek;
	}

	public String getWeeksInRelease() {
		return weeksInRelease;
	}

	public void setWeeksInRelease(String weeksInRelease) {
		this.weeksInRelease = weeksInRelease;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WeeklyData [weekDataKey=" + weekDataKey + ", weekStart="
				+ weekStart + ", weekEnd=" + weekEnd + ", weekRank=" + weekRank
				+ ", weekGross=" + weekGross
				+ ", percentChangeFromFromPrevWeek="
				+ percentChangeFromFromPrevWeek + ", numTheatersForWeek="
				+ numTheatersForWeek + ", percentChangeInTheatersFromPrevWeek="
				+ percentChangeInTheatersFromPrevWeek
				+ ", perTheaterAvgForWeek=" + perTheaterAvgForWeek
				+ ", weeksInRelease=" + weeksInRelease + "]";
	}
}
