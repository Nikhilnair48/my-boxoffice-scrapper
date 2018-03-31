package beans;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DailyData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DailyDataKey dailyDataId;
	private String dailyRank;
	private String dailyGross;
	private String percentChangeFromPrevDate;
	private String percentChangeFromPrevWeek;
	private String numTheaters;
	private String avgPerTheater;
	private String grossToDate;
	private String numDaysInTheater;
	
	// WHAT DO I MEAN BY A CYCLE? A WEEK IS BROKEN INTO TWO CYCLES. 
	// THE PERIOD OVER A WEEKEND (FRI-SUN) OR THE WEEK (MON - THURS) 
	private String rankOverACycle;
	private String grossOverACycle;
	private String averageGrossOverCycle;
	private String theaterChangeFromPreviousCycle;
	private String percentChangeFromFromPrevCycle;
	
	public DailyData() {
		dailyDataId = new DailyDataKey();
		dailyRank = "";
		dailyGross = "";
		percentChangeFromPrevDate = "";
		percentChangeFromPrevWeek = "";
		numTheaters = "";
		avgPerTheater = "";
		grossToDate = "";
		rankOverACycle = "";
		grossOverACycle = "";
		averageGrossOverCycle = "";
		theaterChangeFromPreviousCycle = "";
		percentChangeFromFromPrevCycle = "";
	}
	
	public DailyData(String movieID, String dailyRank, String dailyGross,
			String percentChangeFromPrevDate, String percentChangeFromPrevWeek,
			String numTheaters, String avgPerTheater, String grossToDate,
			String numDaysInTheater, String rankOverACycle,
			String grossOverACycle, String averageGrossOverCycle,
			String theaterChangeFromPreviousCycle, String percentChangeFromFromPrevCycle) {
		super();
		this.dailyDataId = new DailyDataKey();
		this.dailyRank = dailyRank;
		this.dailyGross = dailyGross;
		this.percentChangeFromPrevDate = percentChangeFromPrevDate;
		this.percentChangeFromPrevWeek = percentChangeFromPrevWeek;
		this.numTheaters = numTheaters;
		this.avgPerTheater = avgPerTheater;
		this.grossToDate = grossToDate;
		this.numDaysInTheater = numDaysInTheater;
		this.rankOverACycle = rankOverACycle;
		this.grossOverACycle = grossOverACycle;
		this.averageGrossOverCycle = averageGrossOverCycle;
		this.theaterChangeFromPreviousCycle = theaterChangeFromPreviousCycle;
		this.percentChangeFromFromPrevCycle = percentChangeFromFromPrevCycle;
	}
	
	public DailyDataKey getDailyDataId() {
		return dailyDataId;
	}
	public void setDailyDataId(DailyDataKey dailyDataId) {
		this.dailyDataId = dailyDataId;
	}
	public String getDailyRank() {
		return dailyRank;
	}
	public void setDailyRank(String dailyRank) {
		this.dailyRank = dailyRank;
	}
	public String getDailyGross() {
		return dailyGross;
	}
	public void setDailyGross(String dailyGross) {
		this.dailyGross = dailyGross;
	}
	public String getPercentChangeFromPrevDate() {
		return percentChangeFromPrevDate;
	}
	public void setPercentChangeFromPrevDate(String percentChangeFromPrevDate) {
		this.percentChangeFromPrevDate = percentChangeFromPrevDate;
	}
	public String getPercentChangeFromPrevWeek() {
		return percentChangeFromPrevWeek;
	}
	public void setPercentChangeFromPrevWeek(String percentChangeFromPrevWeek) {
		this.percentChangeFromPrevWeek = percentChangeFromPrevWeek;
	}
	public String getNumTheaters() {
		return numTheaters;
	}
	public void setNumTheaters(String numTheaters) {
		this.numTheaters = numTheaters;
	}
	public String getAvgPerTheater() {
		return avgPerTheater;
	}
	public void setAvgPerTheater(String avgPerTheater) {
		this.avgPerTheater = avgPerTheater;
	}
	public String getGrossToDate() {
		return grossToDate;
	}
	public void setGrossToDate(String grossToDate) {
		this.grossToDate = grossToDate;
	}
	public String getNumDaysInTheater() {
		return numDaysInTheater;
	}
	public void setNumDaysInTheater(String numDaysInTheater) {
		this.numDaysInTheater = numDaysInTheater;
	}
	public String getRankOverACycle() {
		return rankOverACycle;
	}
	public void setRankOverACycle(String rankOverACycle) {
		this.rankOverACycle = rankOverACycle;
	}
	public String getGrossOverACycle() {
		return grossOverACycle;
	}
	public void setGrossOverACycle(String grossOverACycle) {
		this.grossOverACycle = grossOverACycle;
	}
	public String getAverageGrossOverCycle() {
		return averageGrossOverCycle;
	}

	public void setAverageGrossOverCycle(String averageGrossOverCycle) {
		this.averageGrossOverCycle = averageGrossOverCycle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTheaterChangeFromPreviousCycle() {
		return theaterChangeFromPreviousCycle;
	}
	public void setTheaterChangeFromPreviousCycle(
			String theaterChangeFromPreviousCycle) {
		this.theaterChangeFromPreviousCycle = theaterChangeFromPreviousCycle;
	}
	public String getPercentChangeFromFromPrevCycle() {
		return percentChangeFromFromPrevCycle;
	}
	public void setPercentChangeFromFromPrevCycle(
			String percentChangeFromFromPrevCycle) {
		this.percentChangeFromFromPrevCycle = percentChangeFromFromPrevCycle;
	}

	@Override
	public String toString() {
		return "DailyData [dailyDataId=" + dailyDataId + ", dailyRank="
				+ dailyRank + ", dailyGross=" + dailyGross
				+ ", percentChangeFromPrevDate=" + percentChangeFromPrevDate
				+ ", percentChangeFromPrevWeek=" + percentChangeFromPrevWeek
				+ ", numTheaters=" + numTheaters + ", avgPerTheater="
				+ avgPerTheater + ", grossToDate=" + grossToDate
				+ ", numDaysInTheater=" + numDaysInTheater
				+ ", rankOverACycle=" + rankOverACycle + ", grossOverACycle="
				+ grossOverACycle + ", averageGrossOverCycle="
				+ averageGrossOverCycle + ", theaterChangeFromPreviousCycle="
				+ theaterChangeFromPreviousCycle
				+ ", percentChangeFromFromPrevCycle="
				+ percentChangeFromFromPrevCycle + "]";
	}

}
