package beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieSummary {
	
	@Id
	private String movieID;
	private String movieName;
	private String studio;
	private String genre;
	private String budget;
	private String runtime;		// MINUTES
	private String mpaaRating;
	
	private String domesticGross;
	private String internationalGross;
	private String worldwideGross;
	
	private String highestTheaterCount;
	private Long closingDate;
	private Long releaseDate;
	private String daysInRelease;
	
	public MovieSummary() {
		this.movieID = "";
		this.movieName = "";
		this.studio = "";
		this.genre = "";
		this.budget = "";
		this.mpaaRating = "";
		this.runtime = "";
		this.domesticGross = "";
		this.internationalGross = "";
		this.worldwideGross = "";
		this.highestTheaterCount = "";
		//this.closingDate = ;
		//this.releaseDate = ;
		this.daysInRelease = "";	
	}

	public String getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public MovieSummary(String movieID, String movieName, String studio, String genre,
			String budget, String runtime, String mpaaRating, String domesticGross,
			String internationalGross, String worldwideGross,
			String highestTheaterCount, Long closingDate, Long releaseDate,
			String daysInRelease) {
		super();
		this.movieID = movieID;
		this.movieName = movieName;
		this.studio = studio;
		this.genre = genre;
		this.budget = budget;
		this.runtime = runtime;
		this.domesticGross = domesticGross;
		this.internationalGross = internationalGross;
		this.worldwideGross = worldwideGross;
		this.highestTheaterCount = highestTheaterCount;
		this.closingDate = closingDate;
		this.releaseDate = releaseDate;
		this.daysInRelease = daysInRelease;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getDomesticGross() {
		return domesticGross;
	}

	public void setDomesticGross(String domesticGross) {
		this.domesticGross = domesticGross;
	}

	public String getInternationalGross() {
		return internationalGross;
	}

	public void setInternationalGross(String internationalGross) {
		this.internationalGross = internationalGross;
	}

	public String getWorldwideGross() {
		return worldwideGross;
	}

	public void setWorldwideGross(String worldwideGross) {
		this.worldwideGross = worldwideGross;
	}

	public String getHighestTheaterCount() {
		return highestTheaterCount;
	}

	public void setHighestTheaterCount(String highestTheaterCount) {
		this.highestTheaterCount = highestTheaterCount;
	}

	public Long getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Long closingDate) {
		this.closingDate = closingDate;
	}

	public Long getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Long releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDaysInRelease() {
		return daysInRelease;
	}

	public void setDaysInRelease(String daysInRelease) {
		this.daysInRelease = daysInRelease;
	}

	@Override
	public String toString() {
		return "MovieSummary [movieID=" + movieID + ", movieName=" + movieName
				+ ", studio=" + studio + ", genre=" + genre + ", budget="
				+ budget + ", runtime=" + runtime + ", mpaaRating="
				+ mpaaRating + ", domesticGross=" + domesticGross
				+ ", internationalGross=" + internationalGross
				+ ", worldwideGross=" + worldwideGross
				+ ", highestTheaterCount=" + highestTheaterCount
				+ ", closingDate=" + closingDate + ", releaseDate="
				+ releaseDate + ", daysInRelease=" + daysInRelease + "]";
	}

}
