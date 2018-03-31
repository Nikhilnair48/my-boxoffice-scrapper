package beans;

import java.util.ArrayList;

public class Movie {

	private MovieSummary summary;
	private ArrayList<DailyData> daily;
	private ArrayList<WeekendData> weekend;
	private ArrayList<WeeklyData> weekly;
	private ArrayList<ForeignData> foriegn;
	
	public Movie() {
		super();
		this.summary = new MovieSummary();
		this.daily = new ArrayList<>();
		this.weekend = new ArrayList<>();
		this.weekly = new ArrayList<>();
		this.foriegn = new ArrayList<>();
	}

	public Movie(MovieSummary summary, ArrayList<DailyData> daily,
			ArrayList<WeekendData> weekend, ArrayList<WeeklyData> weekly,
			ArrayList<ForeignData> foriegn) {
		super();
		this.summary = summary;
		this.daily = daily;
		this.weekend = weekend;
		this.weekly = weekly;
		this.foriegn = foriegn;
	}



	public MovieSummary getSummary() {
		return summary;
	}

	public void setSummary(MovieSummary summary) {
		this.summary = summary;
	}

	public ArrayList<DailyData> getDaily() {
		return daily;
	}

	public void setDaily(ArrayList<DailyData> daily) {
		this.daily = daily;
	}

	public ArrayList<WeekendData> getWeekend() {
		return weekend;
	}

	public void setWeekend(ArrayList<WeekendData> weekend) {
		this.weekend = weekend;
	}

	public ArrayList<WeeklyData> getWeekly() {
		return weekly;
	}

	public void setWeekly(ArrayList<WeeklyData> weekly) {
		this.weekly = weekly;
	}

	public ArrayList<ForeignData> getForiegn() {
		return foriegn;
	}

	public void setForiegn(ArrayList<ForeignData> foriegn) {
		this.foriegn = foriegn;
	}

	@Override
	public String toString() {
		return "Movie [summary=" + summary + ", daily=" + daily + ", weekend="
				+ weekend + ", weekly=" + weekly + ", foriegn=" + foriegn + "]";
	}
	
	
	
	
}
