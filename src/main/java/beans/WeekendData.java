package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class WeekendData implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WeekendDataKey wkndDataKey;
	private Long weekendEnd;
	private String weekendRank;
	private String weekendGross;
	private String percentChangeFromFromPrevWknd;
	private String numTheatersForWeekend;
	private String percentChangeInTheatersFromPrevWknd;
	private String perTheaterAvgForWknd;
	private String grossToDate;
	private String weeksInRelease;
	
	public WeekendData() {
		this.wkndDataKey = new WeekendDataKey();
		//this.weekendStart = weekendStart;
		//this.weekendEnd = weekendEnd;
		this.weekendRank = "";
		this.weekendGross = "";
		this.percentChangeFromFromPrevWknd = "";
		this.numTheatersForWeekend = "";
		this.percentChangeInTheatersFromPrevWknd = "";
		this.perTheaterAvgForWknd = "";
		this.grossToDate = "";
		this.weeksInRelease = "";
	}

	public WeekendDataKey getWkndDataKey() {
		return wkndDataKey;
	}

	public void setWkndDataKey(WeekendDataKey wkndDataKey) {
		this.wkndDataKey = wkndDataKey;
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

	public String getGrossToDate() {
		return grossToDate;
	}

	public void setGrossToDate(String grossToDate) {
		this.grossToDate = grossToDate;
	}

	public String getWeeksInRelease() {
		return weeksInRelease;
	}

	public void setWeeksInRelease(String weeksInRelease) {
		this.weeksInRelease = weeksInRelease;
	}

	@Override
	public String toString() {
		return "WeekendData [wkndDataKey=" + wkndDataKey + ", weekendEnd="
				+ weekendEnd + ", weekendRank=" + weekendRank
				+ ", weekendGross=" + weekendGross
				+ ", percentChangeFromFromPrevWknd="
				+ percentChangeFromFromPrevWknd + ", numTheatersForWeekend="
				+ numTheatersForWeekend
				+ ", percentChangeInTheatersFromPrevWknd="
				+ percentChangeInTheatersFromPrevWknd
				+ ", perTheaterAvgForWknd=" + perTheaterAvgForWknd
				+ ", grossToDate=" + grossToDate + ", weeksInRelease="
				+ weeksInRelease + "]";
	}
	
}
