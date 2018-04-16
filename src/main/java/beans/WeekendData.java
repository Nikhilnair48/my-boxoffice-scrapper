package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeekendData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//private String movieId;
	//private Long weekendStart;
	private WeekendDataKey wkndDataKey;
	private Long weekendStart;
	private Long weekendEnd;
	private String weekendRank;
	private String weekendGross;
	private String percentChangeFromFromPrevWknd;
	private String numTheatersForWeekend;
	private String percentChangeInTheatersFromPrevWknd;
	private String perTheaterAvgForWknd;
	
	private String weeksInRelease;
	
	public WeekendData() {
		//this.movieId = "";
		this.weekendRank = "";
		this.weekendGross = "";
		this.percentChangeFromFromPrevWknd = "";
		this.numTheatersForWeekend = "";
		this.percentChangeInTheatersFromPrevWknd = "";
		this.perTheaterAvgForWknd = "";
		this.weeksInRelease = "";
	}

	public WeekendData(WeekendDataKey wkndDataKey, Long weekendStart,
			Long weekendEnd, String weekendRank, String weekendGross,
			String percentChangeFromFromPrevWknd, String numTheatersForWeekend,
			String percentChangeInTheatersFromPrevWknd,
			String perTheaterAvgForWknd, String weeksInRelease) {
		super();
		this.wkndDataKey = wkndDataKey;
		this.weekendStart = weekendStart;
		this.weekendEnd = weekendEnd;
		this.weekendRank = weekendRank;
		this.weekendGross = weekendGross;
		this.percentChangeFromFromPrevWknd = percentChangeFromFromPrevWknd;
		this.numTheatersForWeekend = numTheatersForWeekend;
		this.percentChangeInTheatersFromPrevWknd = percentChangeInTheatersFromPrevWknd;
		this.perTheaterAvgForWknd = perTheaterAvgForWknd;
		this.weeksInRelease = weeksInRelease;
	}

	public WeekendDataKey getWkndDataKey() {
		return wkndDataKey;
	}

	public void setWkndDataKey(WeekendDataKey wkndDataKey) {
		this.wkndDataKey = wkndDataKey;
	}

	public Long getWeekendStart() {
		return weekendStart;
	}

	public void setWeekendStart(Long weekendStart) {
		this.weekendStart = weekendStart;
	}

	public Long getWeekendEnd() {
		return weekendEnd;
	}

	public void setWeekendEnd(Long weekendEnd) {
		this.weekendEnd = weekendEnd;
	}

	public String getWeekendRank() {
		return weekendRank;
	}

	public void setWeekendRank(String weekendRank) {
		this.weekendRank = weekendRank;
	}

	public String getWeekendGross() {
		return weekendGross;
	}

	public void setWeekendGross(String weekendGross) {
		this.weekendGross = weekendGross;
	}

	public String getPercentChangeFromFromPrevWknd() {
		return percentChangeFromFromPrevWknd;
	}

	public void setPercentChangeFromFromPrevWknd(
			String percentChangeFromFromPrevWknd) {
		this.percentChangeFromFromPrevWknd = percentChangeFromFromPrevWknd;
	}

	public String getNumTheatersForWeekend() {
		return numTheatersForWeekend;
	}

	public void setNumTheatersForWeekend(String numTheatersForWeekend) {
		this.numTheatersForWeekend = numTheatersForWeekend;
	}

	public String getPercentChangeInTheatersFromPrevWknd() {
		return percentChangeInTheatersFromPrevWknd;
	}

	public void setPercentChangeInTheatersFromPrevWknd(
			String percentChangeInTheatersFromPrevWknd) {
		this.percentChangeInTheatersFromPrevWknd = percentChangeInTheatersFromPrevWknd;
	}

	public String getPerTheaterAvgForWknd() {
		return perTheaterAvgForWknd;
	}

	public void setPerTheaterAvgForWknd(String perTheaterAvgForWknd) {
		this.perTheaterAvgForWknd = perTheaterAvgForWknd;
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
		return "WeekendData [wkndDataKey=" + wkndDataKey + ", weekendStart="
				+ weekendStart + ", weekendEnd=" + weekendEnd
				+ ", weekendRank=" + weekendRank + ", weekendGross="
				+ weekendGross + ", percentChangeFromFromPrevWknd="
				+ percentChangeFromFromPrevWknd + ", numTheatersForWeekend="
				+ numTheatersForWeekend
				+ ", percentChangeInTheatersFromPrevWknd="
				+ percentChangeInTheatersFromPrevWknd
				+ ", perTheaterAvgForWknd=" + perTheaterAvgForWknd
				+ ", weeksInRelease=" + weeksInRelease + "]";
	}
	
}
