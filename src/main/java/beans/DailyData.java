package beans;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table( name="DailyData" )
public class DailyData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DailyDataKey dailyDataId;
	//private String movieID;
	//private String date;		// STORE THE DATE AS TIME FROM 1970 (LONG)
	private String date;		// STORE THE DATE AS TIME FROM 1970 (LONG)
	private String dailyRank;
	private String dailyGross;
	private String percentChangeFromPrevDate;
	private String percentChangeFromPrevWeek;
	private String numTheaters;
	private String avgPerTheater;
	private String grossToDate;
	
	
	// WHAT DO I MEAN BY A CYCLE? A WEEK IS BROKEN INTO TWO CYCLES. 
	// THE PERIOD OVER A WEEKEND (FRI-SUN) OR THE WEEK (MON - THURS) 
	private String rankOverACycle;
	private String grossOverACycle;
	private String averageGrossOverCycle;
	private String theaterChangeFromPreviousCycle;
	private String percentChangeFromFromPrevCycle;
	
	public DailyData() {
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

	public DailyData(DailyDataKey dailyDataId, String date, String dailyRank,
			String dailyGross, String percentChangeFromPrevDate,
			String percentChangeFromPrevWeek, String numTheaters,
			String avgPerTheater, String grossToDate, String rankOverACycle,
			String grossOverACycle, String averageGrossOverCycle,
			String theaterChangeFromPreviousCycle,
			String percentChangeFromFromPrevCycle) {
		super();
		this.dailyDataId = dailyDataId;
		this.date = date;
		this.dailyRank = dailyRank;
		this.dailyGross = dailyGross;
		this.percentChangeFromPrevDate = percentChangeFromPrevDate;
		this.percentChangeFromPrevWeek = percentChangeFromPrevWeek;
		this.numTheaters = numTheaters;
		this.avgPerTheater = avgPerTheater;
		this.grossToDate = grossToDate;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DailyData [dailyDataId=" + dailyDataId + ", date=" + date
				+ ", dailyRank=" + dailyRank + ", dailyGross=" + dailyGross
				+ ", percentChangeFromPrevDate=" + percentChangeFromPrevDate
				+ ", percentChangeFromPrevWeek=" + percentChangeFromPrevWeek
				+ ", numTheaters=" + numTheaters + ", avgPerTheater="
				+ avgPerTheater + ", grossToDate=" + grossToDate
				+ ", rankOverACycle=" + rankOverACycle + ", grossOverACycle="
				+ grossOverACycle + ", averageGrossOverCycle="
				+ averageGrossOverCycle + ", theaterChangeFromPreviousCycle="
				+ theaterChangeFromPreviousCycle
				+ ", percentChangeFromFromPrevCycle="
				+ percentChangeFromFromPrevCycle + "]";
	}
}
