package myboxofficescrapper;

import java.text.Collator;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import util.Constants;
import beans.DailyData;
import beans.DailyDataKey;
import beans.ForeignData;
import beans.ForeignDataKey;
import beans.MovieSummary;
import beans.WeekendData;
import beans.WeekendDataKey;
import beans.WeeklyData;
import beans.WeeklyDataKey;

public class FetchDataDelegates {
	
	public enum Months {
		JANUARY,
		FEBRUARY,
		MARCH,
		APRIL,
		MAY,
		JUNE,
		JULY,
		AUGUST,
		SEPTEMBER,
		OCTOBER,
		NOVEMBER,
		DECEMBER
	}
	
	public enum MonthsShort {
		JAN,
		FEB,
		MAR,
		APR,
		MAY,
		JUN,
		JUL,
		AUG,
		SEP,
		OCT,
		NOV,
		DEC
	}
	
	private static final Logger logger = (Logger) LogManager.getLogger(FetchDataDelegates.class);
	
	public static MovieSummary fetchMovieSummary(String movieId) {
		MovieSummary summary = new MovieSummary(); 
		
		String ID = movieId.substring(movieId.indexOf("id=")+3, movieId.indexOf(".htm"));
		String link = "http://www.boxofficemojo.com/movies/?page=main&id=" + ID + ".htm";

		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(FetchData.getHTMLFromURL(link).toString());
		Element body = doc.getElementById(Constants.rootElementInBaseLink);
		//Element summaryTable = body.getElementsByTag("table").get(5).getElementsByTag("td").get(1);
		if(body != null && body.getElementsByTag("table").size() > 5) {
			
		
			//summary.setMovieName(summaryTable.getElementsByTag("font").text());
			Elements els = body.getElementsByTag("table").get(5).getElementsByTag("td");
			
			summary.setMovieID(movieId);
			if(body.getElementsByTag("table").get(3).getElementsByTag("td").get(1) != null &&
					body.getElementsByTag("table").get(3).getElementsByTag("td").get(1).getElementsByTag("font").get(0) != null)
			summary.setMovieName(body.getElementsByTag("table").get(3).getElementsByTag("td").get(1).getElementsByTag("font").get(0).text());
			
			//summary.setDomesticGross(els.get(0).getElementsByTag("td").text());
			els.forEach((el) -> {
				if(el.text().toLowerCase().contains("domestic total gross")) {
					summary.setDomesticGross(el.text());
				} else if(el.text().toLowerCase().contains("distributor")) {
					summary.setStudio(el.text());
				} else if(el.text().toLowerCase().contains("release date")) {
					summary.setReleaseDate(getDateFromFormat(el.text()));
				} else if(el.text().toLowerCase().contains("genre")) {
					summary.setGenre(el.text());
				} else if(el.text().toLowerCase().contains("runtime")) {
					summary.setRuntime(el.text());
				} else if(el.text().toLowerCase().contains("mpaa rating")) {
					summary.setMpaaRating(el.text());
				} else if(el.text().toLowerCase().contains("production budger")) {
					summary.setBudget(el.text());
				}
			});
			
			/*if(els.get(0).getElementsByTag("td").size() >= 1)
				summary.setStudio(els.get(1).getElementsByTag("td").get(0).text());
			if(els.get(0).getElementsByTag("td").size() >= 2)
				summary.setReleaseDate(getDateFromFormat(els.get(1).getElementsByTag("td").get(1).text()));
			if(els.get(1).getElementsByTag("td").size() >= 1)
				summary.setGenre(els.get(2).getElementsByTag("td").get(0).text());
			if(els.get(1).getElementsByTag("td").size() >= 2)
				summary.setRuntime(els.get(2).getElementsByTag("td").get(1).text());
			if(els.get(2).getElementsByTag("td").size() >= 1)
				summary.setMpaaRating(els.get(3).getElementsByTag("td").get(0).text());
			if(els.get(2).getElementsByTag("td").size() >= 2)
				summary.setBudget(els.get(3).getElementsByTag("td").get(1).text());*/
			
			Element summarySection = body.getElementsByTag("table").get(8);
			Elements summaryContent = summarySection.getElementsByClass("mp_box_content");
			
			Elements lifeTimeGross = null;
			if(summaryContent.size() > 0) {		// ASSUMING THAT THE FIRST ONE IS "Total Lifetime Grosses"; TO BE CHANGED LATER
				lifeTimeGross = summaryContent.get(0).getElementsByTag("tr");
				lifeTimeGross.forEach((tr) -> {
					if(tr.text().toLowerCase().contains("foreign")) {
						summary.setInternationalGross(tr.text());
					} else if(tr.text().toLowerCase().contains("worldwide")) {
						summary.setWorldwideGross(tr.text());
					}
				});
			}
			
			Elements domesticSummary = null;
			if(summaryContent.size() > 1) {		// ASSUMING THAT THE FIRST ONE IS "Domestic Summary"; TO BE CHANGED LATER
				domesticSummary = summaryContent.get(1).getElementsByTag("table");
				domesticSummary.forEach((table) -> {
					if(table.text().toLowerCase().contains("widest release")) {
						summary.setHighestTheaterCount(table.text());
					} else if(table.text().toLowerCase().contains("close date")) {
						summary.setClosingDate(getDateFromFormat(table.text()));
					} else if(table.text().toLowerCase().contains("in release")) {
						summary.setDaysInRelease(table.text());
					}
				});
			}
		
		}
		//logger.info(summary);
		return summary;
	}
	
	///movies/?id=ateam.htm
	public static ArrayList<DailyData> fetchDailyDataForMovieID(String movieId) {
		
		ArrayList<DailyData> dailyData = new ArrayList<DailyData>(); 
		//http://www.boxofficemojo.com/movies/?page=daily&id=starwars7.htm
		String ID = movieId.substring(movieId.indexOf("id=")+3, movieId.indexOf(".htm"));
		String link = "http://www.boxofficemojo.com/movies/?page=daily&id=" + ID + ".htm";
		// GETS US TO THE DAILY TABLES: document.getElementById("body").children[1].children[0].children[0].children[0].children[3]
		// GET US MUCH CLOSER TO THE TABLES: document.getElementById("body").children[1].children[0].children[0].children[0].children[3].children[0].children[0].children[0].children[3]
		// -- SWITCHES BETWEEN FONT/TABLE. TEXT IN FONT CONTAINS "MONTH YEAR." TABLE CONTAINS THE DAILY DATA.
		
		// 6 TABLES MAX 
		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(FetchData.getHTMLFromURL(link).toString());
		Element bodyElement = doc.getElementById(Constants.rootElementInBaseLink);
		Element dailyTables = bodyElement.child(1).child(0).child(0).child(0).child(3).child(0).child(0).child(0).child(3);
		
		//for(int i = 0; i < dailyTables.childNodes().size()-1; i++) {
			//dailyTables.childNodes()
		Node node = dailyTables.child(0);
		List<Node> monthlyDataRows = dailyTables.child(1).childNodes();
		
		for(int i = 0; i < dailyTables.childNodeSize()-1; i++) {		// LOOP THROUGH ALL THE TABLES ON THE PAGE. EXCLUDE THE LAST ELEMENT (IS AND "I" ELEMENT)
			Element els = dailyTables.child(i);
			Elements ee = els.getElementsByTag("td");
			
			for(int k = 0; k < ee.size(); k++) {
				if(ee.get(k).getElementsByTag("center") != null) {
					//THE LENGTH COULD BE 19, BUT IT'S JUST HEADER INFO -- IGNORE
					if(ee.get(k) != null && ee.get(k).children().size() > 0 && ee.get(k).children().first().hasAttr("href")) {
					// dailyDataArray[0].split("\u00A0")[0]
						DailyData daily = saveDailyData(ee.get(k).text());
						DailyDataKey key = new DailyDataKey();
						String linkWithDate = ee.get(k).children().first().attr("href");
						key.setMovieID(ID);
						LocalDate date = LocalDate.parse(linkWithDate.substring(linkWithDate.indexOf("=")+1, linkWithDate.indexOf("&")));
						key.setDate(String.valueOf(date.toEpochDay()));
						daily.setDailyDataId(key);
						dailyData.add(daily);
						//logger.info(daily);
					}
				}
			}
			
		}
		return dailyData;
	}
	
	// SAMPLE DATA - [11 2 , $9,660,560, -, /, -, 3,535, /, $2,733, $9,660,560, /, 1]
	public static DailyData saveDailyData(String dailyData) {
		DailyData daily = new DailyData();
		String[] dailyDataArray = dailyData.split(" ");
		
		if(dailyDataArray.length == 17) {		// CYCLICAL DATA AVAILABLE - MORE DETAILS
			//[13 2 ,  2, $6,651,316, $25,669,455, -28.9%, /, -, -, /, -, 3,535, /, $1,882, $7,262, $25,669,455, /, 3]
			
			String[] dateAndRank = dailyDataArray[0].split("\u00A0");		// SPACE - " " DOESN'T WORK
			
			if(dateAndRank.length > 1)
				daily.setDailyRank(dateAndRank[1]);		// DAILYDATAARRAY[0] = "11 2", 11 IS THE DATE, 2 IS THE DAILY RANK. WE NEED THE DAILY RANK
			
			daily.setRankOverACycle(dailyDataArray[1].replaceAll("\u00A0", ""));
			daily.setDailyGross(dailyDataArray[2]);
			daily.setGrossOverACycle(dailyDataArray[3]);
			
			daily.setPercentChangeFromPrevDate(dailyDataArray[4]);
			daily.setPercentChangeFromPrevWeek(dailyDataArray[6]);
			
			daily.setTheaterChangeFromPreviousCycle(dailyDataArray[7]);
			daily.setPercentChangeFromFromPrevCycle(dailyDataArray[9]);
			
			daily.setNumTheaters(dailyDataArray[10]);
			daily.setAvgPerTheater(dailyDataArray[12]);
			
			daily.setAverageGrossOverCycle(dailyDataArray[13]);
			
			daily.setGrossToDate(dailyDataArray[14]);
			daily.setNumDaysInTheater(dailyDataArray[16]);
			
		} else if(dailyDataArray.length == 11) {	// PRIMARILY DAILY DATA
			String[] dateAndRank = dailyDataArray[0].split("\u00A0");
			
			if(dateAndRank.length > 1)
				daily.setDailyRank(dateAndRank[1]);		// DAILYDATAARRAY[0] = "11 2", 11 IS THE DATE, 2 IS THE DAILY RANK. WE NEED THE DAILY RANK
			
			daily.setDailyGross(dailyDataArray[1]);
			
			daily.setPercentChangeFromPrevDate(dailyDataArray[2]);
			daily.setPercentChangeFromPrevWeek(dailyDataArray[4]);
			
			daily.setNumTheaters(dailyDataArray[5]);
			daily.setAvgPerTheater(dailyDataArray[7]);
			
			daily.setGrossToDate(dailyDataArray[8]);
			daily.setNumDaysInTheater(dailyDataArray[10]);
		}
		
		return daily;
	}
	
	public static ArrayList<ForeignData> fetchInternationalDataForMovieID(String movieId, Long movieReleaseYear) {
		int totalCount = 0;
		String ID = movieId.substring(movieId.indexOf("id=")+3, movieId.indexOf(".htm"));
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
		
			ArrayList<ForeignData> foreignData = new ArrayList<>(); 
		
			//LINK TO CONSTRUCT: http://www.boxofficemojo.com/movies/?page=intl&id=beautyandthebeast2017.htm
			//http://www.boxofficemojo.com/movies/?page=intl&id=likefatherlikeson.htm
			String link = "http://www.boxofficemojo.com/movies/?page=intl&id=" + ID + ".htm";
			try {
				Document doc = Jsoup.connect(link).get();
				Element body = doc.getElementById(Constants.rootElementInBaseLink);
				int size = body.getElementsByTag("table").size();

				// IF THE TITLE AND THE YEAR WITHIN THE PAGE TITLE DOESN'T MATCH - 
				// WE'VE GONE TOO FAR BACK, AND THERE AREN'T ANY RECORDS
				if(body != null && body.getElementsByTag("table").size() > 1
						&& body.getElementsByTag("table").get(body.getElementsByTag("table").size()-1).getElementsByTag("tbody").first() != null ) {
				
					Elements intlCountries = body.getElementsByTag("table").get(body.getElementsByTag("table").size()-1)
												.getElementsByTag("tbody").first().getElementsByTag("tr");
					//logger.info("TOTAL ROWS: " + intlCountries.size());
					Elements tableDefinition = intlCountries.get(0).getAllElements().get(0).children();
						
					columnMap = setColumnHeaderPositions(tableDefinition);
						
					for (int j = 1; j < intlCountries.size(); j++) {
						ForeignData countryData = new ForeignData();
							
						Elements currentCountry = intlCountries.get(j).getAllElements().get(0).children();
						if (currentCountry.size() >= 1 && currentCountry.get(columnMap.get("Country (click to view weekend breakdown)")).text() != null 
							&& currentCountry.get(columnMap.get("Country (click to view weekend breakdown)")).text().length() > 0) {
								
						if(columnMap.containsKey("Country (click to view weekend breakdown)") 
							&& currentCountry.size() >= columnMap.get("Country (click to view weekend breakdown)")+1) {
							countryData.setFDKey(new ForeignDataKey(movieId, currentCountry.get(columnMap.get("Country (click to view weekend breakdown)")).text()));
						}
								
						if(columnMap.containsKey("Dist.")
							&& currentCountry.size() >= columnMap.get("Dist.")+1) {
							countryData.setDistributor(currentCountry.get(columnMap.get("Dist.")).text());
						}
								
						if (columnMap.containsKey("Release Date") && currentCountry.size() >= columnMap.get("Release Date") + 1
								&& !currentCountry.get(columnMap.get("Release Date")).text().toLowerCase().contains("n/a")) {
							LocalDate date = null;
							if (movieReleaseYear != null) {
								DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault());
								String dateStr = dtf.format(Instant.ofEpochSecond(movieReleaseYear));
								date = LocalDate.parse(dateStr);
							}
							LocalDate releaseDate = formatDate(currentCountry.get(columnMap.get("Release Date")).text(), (date == null ? 20 : date.getYear()));
							if(releaseDate != null)
								countryData.setRelease_date(releaseDate.toEpochDay());
						}
								
						if(columnMap.containsKey("Opening Wknd")
							&& currentCountry.size() >= columnMap.get("Opening Wknd")+1) {
							String openingWknd = currentCountry.get(columnMap.get("Opening Wknd")).text();
							
							if(!openingWknd.toLowerCase().equals("n/a") && openingWknd.length() > 1)
								countryData.setOpening_weekend(cleanUpCommas(openingWknd.substring(1)));
							}
															
							if(columnMap.containsKey("Total Gross / As Of")
								&& currentCountry.size() >= columnMap.get("Total Gross / As Of")+1) {
								String totalGross = currentCountry.get(columnMap.get("Total Gross / As Of")).text();
								if(!totalGross.toLowerCase().equals("n/a") && totalGross.length() > 1)
									countryData.setTotal_gross(cleanUpCommas(totalGross));
		
								if(currentCountry.get(columnMap.get("Total Gross / As Of")+1) != null) {
									String closeDate = currentCountry.get(columnMap.get("Total Gross / As Of")+1).text();
									if(closeDate != null && closeDate.length() > 1 && !closeDate.toLowerCase().contains("n/a")) {
										LocalDate date = null;
										if(movieReleaseYear != null) {
											DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault());
											String dateStr = dtf.format(Instant.ofEpochSecond(movieReleaseYear));
											date = LocalDate.parse(dateStr);
										}
										LocalDate closingDate = formatDate(closeDate, (date == null ? 20 : date.getYear()));
										if(closingDate != null)
											countryData.setClosing_date(closingDate.toEpochDay());
									}
								}							
							}
							//logger.info(countryData);
							foreignData.add(countryData);
								
						}
					}
				}
			} catch(Exception ex) {
				//logger.error(Arrays.toString(ex.getStackTrace()));
				ex.printStackTrace();
			}
			
			//logger.info(foreignData);
			return foreignData;
		}
	
	public static ArrayList<WeekendData> fetchWeekendDataForMovieID(String movieId) {
		ArrayList<WeekendData> weekendData = new ArrayList<>();
		
		String ID = movieId.substring(movieId.indexOf("id=")+3, movieId.indexOf(".htm"));
		String link = "http://www.boxofficemojo.com/movies/?page=weekend&id=" + ID + ".htm";
		
		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(FetchData.getHTMLFromURL(link).toString());
		Element bodyElement = doc.getElementById(Constants.rootElementInBaseLink);
		if(bodyElement != null) {
			
			Element weekendTableAndYear = bodyElement.getElementsByTag("center").get(1);
			
			if(weekendTableAndYear.getElementsByTag("table").size() > 0) {
				Elements weekendTable = weekendTableAndYear.getElementsByTag("table").get(0).getElementsByTag("tr");
				Elements weekendTableYear = weekendTableAndYear.getElementsByTag("b");
				
				for(int i = 1; i < weekendTable.size(); i++) {
					WeekendData wkndData = new WeekendData();
					
					// GET THE YEAR AND WEEK FROM THE HYPERLINK
					Elements weekendTr = weekendTable.get(i).getElementsByTag("td");
					String year = null, sWeekNumber = null;
					String wkndDataLink = null;
					if(weekendTr.get(0).getElementsByTag("a").hasAttr("href")) {
						wkndDataLink = weekendTr.get(0).getElementsByTag("a").attr("href");
						year = wkndDataLink.substring(wkndDataLink.indexOf("yr=")+3, wkndDataLink.indexOf("yr=")+7);
						sWeekNumber = wkndDataLink.substring(wkndDataLink.indexOf("wknd=")+5, wkndDataLink.indexOf("wknd=")+7); //wknd=51
					}
					
					// GET THE TEXT (EX: "DEC 18-20") TO DETERMINE WHEN THE WEEK ENDED
					String weekendDates = weekendTr.get(0).text();
					String[] dates = weekendDates.split("-");
					
					
					WeekFields weekFields = WeekFields.of(Locale.US);
					LocalDate lDate = LocalDate.now()
							.withYear(Integer.parseInt(year))
							.with(weekFields.weekOfYear(), Integer.parseInt(sWeekNumber));
	
					wkndData.setWkndDataKey(new WeekendDataKey(movieId, lDate.toEpochDay()));
					wkndData.setWeekendEnd(determindWeekendEndDate(weekendDates, year).toEpochDay());
					wkndData.setWeekendRank(weekendTr.get(1).text());
					wkndData.setWeekendGross(weekendTr.get(2).text());
					wkndData.setPercentChangeFromFromPrevWknd(weekendTr.get(3).text());
					wkndData.setNumTheatersForWeekend(weekendTr.get(4).text());
					wkndData.setPercentChangeInTheatersFromPrevWknd(weekendTr.get(5).text());
					wkndData.setPerTheaterAvgForWknd(weekendTr.get(6).text());
					wkndData.setGrossToDate(weekendTr.get(7).text());
					wkndData.setWeeksInRelease(weekendTr.get(8).text());
					
					weekendData.add(wkndData);
				}
			}
		}
		return weekendData;
	}
	
	public static ArrayList<WeeklyData> fetchWeeklyDataForMovieID(String movieId) {
		ArrayList<WeeklyData> weeklyData = new ArrayList<>();
		
		String ID = movieId.substring(movieId.indexOf("id=")+3, movieId.indexOf(".htm"));
		String link = "http://www.boxofficemojo.com/movies/?page=weekly&id=" + ID + ".htm";
		
		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(FetchData.getHTMLFromURL(link).toString());
		Element bodyElement = doc.getElementById(Constants.rootElementInBaseLink);
		if(bodyElement != null) {
			
			Element weekTableAndYear = bodyElement.getElementsByTag("center").get(1);
			
			if(weekTableAndYear.getElementsByTag("table").size() > 0) {
				Elements weekTable = weekTableAndYear.getElementsByTag("table").get(0).getElementsByTag("tr");
				Elements weekTableYear = weekTableAndYear.getElementsByTag("b");
				
				for(int i = 1; i < weekTable.size(); i++) {
					WeeklyData wkData = new WeeklyData();
					
					// GET THE YEAR AND WEEK FROM THE HYPERLINK
					Elements weekendTr = weekTable.get(i).getElementsByTag("td");
					String year = null, sWeekNumber = null;
					String wkndDataLink = null;
					if(weekendTr.get(0).getElementsByTag("a").hasAttr("href")) {
						wkndDataLink = weekendTr.get(0).getElementsByTag("a").attr("href");
						year = wkndDataLink.substring(wkndDataLink.indexOf("yr=")+3, wkndDataLink.indexOf("yr=")+7);
						sWeekNumber = wkndDataLink.substring(wkndDataLink.indexOf("wk=")+3, wkndDataLink.indexOf("wk=")+5); //wknd=51
					}
					
					// GET THE TEXT (EX: "DEC 18-20") TO DETERMINE WHEN THE WEEK ENDED
					String weekendDates = weekendTr.get(0).text();
					String[] dates = weekendDates.split("-");
					
					WeekFields weekFields = WeekFields.of(Locale.US);
					LocalDate lDate = LocalDate.now()
							.withYear(Integer.parseInt(year))
							.with(weekFields.weekOfYear(), Integer.parseInt(sWeekNumber));
	
					wkData.setWeekDataKey(new WeeklyDataKey(movieId, lDate.toEpochDay()));
					wkData.setWeekEnd(determindWeekendEndDate(weekendDates, year).toEpochDay());
					wkData.setWeekRank(weekendTr.get(1).text());
					wkData.setWeekGross(weekendTr.get(2).text());
					wkData.setPercentChangeFromFromPrevWeek(weekendTr.get(3).text());
					wkData.setNumTheatersForWeek(weekendTr.get(4).text());
					wkData.setPercentChangeInTheatersFromPrevWeek(weekendTr.get(5).text());
					wkData.setPerTheaterAvgForWeek(weekendTr.get(6).text());
					wkData.setGrossToDate(weekendTr.get(7).text());
					wkData.setWeeksInRelease(weekendTr.get(8).text());
					
					weeklyData.add(wkData);
				}
			}
		}
		
		//logger.info(weeklyData);
		
		return weeklyData;
	}
	
	public static String cleanUpCommas(String value) {
		if(value != null && value.length() > 0) {
			value = value.replaceAll("$", "").replaceAll(",", "");
			value = value.replaceAll("%", "").replaceAll(",", "");
			if(value.length() > 0 && ((int)value.charAt(0)) == 36) {
				value = value.substring(1);
			}
		}
		return value;
	}
	
	public static LocalDate formatDate(String value, int year) throws ParseException {
		// RETURN IF THE FORMAT IS DIFFERENT
		if(!value.contains("/"))	return null;
		
		String[] values = value.split("/");
		String  month = (values[0].length() == 1 ? "0" + values[0] : values[0]);
		String date = (values[1].length() == 1 ? "0" + values[1] : values[1]);
		String yearValue = null;
		// SOMETIMES THE YEAR THAT'S BEING PASSED BE NOT BE CORRECT.
		// EX: FOR CLOSING DATES: A MOVIE MAY HAVE RELEASED IN '08 BUT CLOSED IN '09. SO WE NEED TO ENSURE THAT
		// THE CLOSING DATE IS NOT '08.
		if(String.valueOf(year).substring(2).equals(values[2]))
			yearValue = String.valueOf(year);
		else
			yearValue = String.valueOf(year).substring(0,2) + values[2];
		
		//SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		/*Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, yearInValue);
		cal.set(Calendar.MONTH, month-1);	// MONTHS ARE 0-INDEXED
		cal.set(Calendar.DATE, date);
		
		java.sql.Date movieDate = new java.sql.Date(cal.getTimeInMillis());*/
		
		LocalDate localDate = null; 
		if(yearValue != null) {
			localDate = LocalDate.parse(yearValue + "-" + month + "-" + date, DateTimeFormatter.ISO_LOCAL_DATE);
		}
		
		return localDate;//sdf.format(movieDate);
	}

	public static Long getDateFromFormat(String dateFormat) {
		//els.get(1).getElementsByTag("td").get(1).text()
		//June 11, 2010
		LocalDate finalDate = null;
		String date = cleanUpCommas(dateFormat.substring(dateFormat.indexOf("Release Date: ") + new String("Release Date: ").length()));
		String[] dateAsArray = date.split(" ");
		if(dateAsArray != null && dateAsArray.length == 3) {
			String month = convertMonthTextToNum(dateAsArray[0]);
			finalDate = LocalDate.parse(dateAsArray[2] + "-" + String.valueOf(month) + "-" + (dateAsArray[1].length() == 1 ? "0" + String.valueOf(dateAsArray[1]) : dateAsArray[1] ));
		}
		return (finalDate == null ? null : finalDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
	}
	
	public static String convertMonthShortTextToNum(String month) {
		String monthNum = null;
		month = month.toUpperCase().trim().replaceAll("\u00A0", "");
		Collator usCollator = Collator.getInstance(Locale.US);		// USING COLLATOR SINCE SIMPLE .EQUALS COMPARISON FAILS WHEN UNICODE IS DIFFERENT
		usCollator.setStrength(Collator.PRIMARY);
		
		if(usCollator.compare(MonthsShort.JAN.toString(), month) == 0) {
			monthNum = "01";
		} else if(usCollator.compare(MonthsShort.FEB.toString(), month) == 0) {
			monthNum = "02";
		} else if(usCollator.compare(MonthsShort.MAR.toString(), month) == 0) {
			monthNum = "03";
		} else if(usCollator.compare(MonthsShort.APR.toString(), month) == 0) {
			monthNum = "04";
		} else if(usCollator.compare(MonthsShort.MAY.toString(), month) == 0) {
			monthNum = "05";
		} else if(usCollator.compare(MonthsShort.JUN.toString(), month) == 0) {
			monthNum = "06";
		} else if(usCollator.compare(MonthsShort.JUL.toString(), month) == 0) {
			monthNum = "07";
		} else if(usCollator.compare(MonthsShort.AUG.toString(), month) == 0) {
			monthNum = "08";
		} else if(usCollator.compare(MonthsShort.SEP.toString(), month) == 0) {
			monthNum = "09";
		} else if(usCollator.compare(MonthsShort.OCT.toString(), month) == 0) {
			monthNum = "10";
		} else if(usCollator.compare(MonthsShort.NOV.toString(), month) == 0) {
			monthNum = "11";
		} else if(usCollator.compare(MonthsShort.DEC.toString(), month) == 0) {
			monthNum = "12";
		}
		
		return monthNum;
	}
	
	public static String convertMonthTextToNum(String month) {
		String monthNum = null;
		month = month.toUpperCase();
		if(Months.JANUARY.toString().equals(month)) {
			monthNum = "01";
		} else if(Months.FEBRUARY.toString().equals(month)) {
			monthNum = "02";
		} else if(Months.MARCH.toString().equals(month)) {
			monthNum = "03";
		} else if(Months.APRIL.toString().equals(month)) {
			monthNum = "04";
		} else if(Months.MAY.toString().equals(month)) {
			monthNum = "05";
		} else if(Months.JUNE.toString().equals(month)) {
			monthNum = "06";
		} else if(Months.JULY.toString().equals(month)) {
			monthNum = "07";
		} else if(Months.AUGUST.toString().equals(month)) {
			monthNum = "08";
		} else if(Months.SEPTEMBER.toString().equals(month)) {
			monthNum = "09";
		} else if(Months.OCTOBER.toString().equals(month)) {
			monthNum = "10";
		} else if(Months.NOVEMBER.toString().equals(month)) {
			monthNum = "11";
		} else if(Months.DECEMBER.toString().equals(month)) {
			monthNum = "12";
		}
		return monthNum;
	}
	
	public static LocalDate determindWeekendEndDate(String str, String year) {
		String[] data = str.split(" ");		// SPLIT DATA "JAN 22-24" SO FOCUS ON THE DATES. OTHER KNOWN CASES: "MAY 6-8,"APR 8-10," "APR 29-MAY 1"
		LocalDate date = null;
		String endDate = null;
		String endMonth = null;
		
		//\u00A0
		String[] dateRange = data[1].replaceAll("[^\\x00-\\x7F]", "-").split("-");
		
		// CASE : "JUL 30-AUG 1"
		if(data.length == 3) {	
			endMonth = convertMonthShortTextToNum(dateRange[1]);		//.substring(0, dateRange[1].indexOf(" "))
			endDate = (data[2].length() == 1 ? "0" + data[2] : data[2]);
			/*endDate = (dateRange[1].substring(dateRange[1].indexOf(" ")).length() == 1 
					? "0" + dateRange[1].substring(dateRange[1].indexOf(" ")) : 
						dateRange[1].substring(dateRange[1].indexOf(" ")));*/
		} else {
			endMonth = convertMonthShortTextToNum(data[0]);
			endDate = (dateRange[1].length() == 1 ? "0" + dateRange[1] : dateRange[1]);
		}
		
		/*if(data[1].length() == 4) {								// CASE: "22-24" > WE GET "JAN 2224"
			//startDate = data[1].substring(0, 2);							// SEPERATE "22"
			endDate = (data[2].length() == 1 ? "0" + data[2] : data[2]);		// SEPERATE "24"
			endMonth = convertMonthShortTextToNum(data[0]);
		} else if(data[1].length() == 3) {							// CASE "JAN 8-10" > WE GET "JAN 810"
			//startDate = data[1].substring(0, 1);							// SEPERATE "8"
			endDate = (data[2].length() == 1 ? "0" + data[2] : data[2]);		// SEPERATE "10"
			endMonth = convertMonthShortTextToNum(data[0]);
		} else if(data[1].length() == 2) {							// CASE "MAY 6-8" > WE GET "MAY 68"
			//startDate = data[1].substring(0, 1);							// SEPERATE "6"
			endDate = (data[2].length() == 1 ? "0" + data[2] : data[2]);		// SEPERATE "8"
			endMonth = convertMonthShortTextToNum(data[0]);
		} else if(data.length == 3) {								
			//startDate = data[1].substring(0, 2);
			endDate = (data[2].length() == 1 ? "0" + data[2] : data[2]);		// SEPARATE "30"
			endMonth = convertMonthShortTextToNum(data[1].substring(2));	// SEPERATE "AUG" AND TRANSLATE TO "10"
		}*/
		
		date = LocalDate.parse(year + "-" + endMonth + "-" + endDate, DateTimeFormatter.ISO_LOCAL_DATE);
		return date;
	}
	
	public static HashMap<String, Integer> setColumnHeaderPositions(Elements tableDefinition) {
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>(); 
		for(int i = 0; i < tableDefinition.size(); i++) {
			columnMap.put(tableDefinition.get(i).text(), (i));
		}
		
		return columnMap;
	}
	
}
