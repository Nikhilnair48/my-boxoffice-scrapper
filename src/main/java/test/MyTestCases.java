package test;

import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.sun.media.jfxmedia.logging.Logger;

import beans.DailyData;
import beans.ForeignData;
import beans.MovieSummary;
import beans.WeekendData;
import myboxofficescrapper.FetchDataDelegates;

public class MyTestCases {

	//@Test
	public void epochTimeFromConversion() {
		String dateFormat = "Release Date: July 30, 2004";
		
		LocalDate finalDate = null;
		String date = FetchDataDelegates.cleanUpCommas(dateFormat.substring(dateFormat.indexOf("Release Date: ") + new String("Release Date: ").length()));
		String[] dateAsArray = date.split(" ");
		if(dateAsArray != null && dateAsArray.length == 3) {
			String month = FetchDataDelegates.convertMonthTextToNum(dateAsArray[0]);
			finalDate = LocalDate.parse(dateAsArray[2] + "-" + String.valueOf(month) + "-" + (dateAsArray[1].length() == 1 ? "0" + String.valueOf(dateAsArray[1]) : dateAsArray[1] ));
		}
		ZoneId zoneId = ZoneId.systemDefault();

		//System.out.println(finalDate);
		Assert.assertEquals(finalDate.atStartOfDay(zoneId).toEpochSecond(), 1091160000L);
		
	}
	
	//@Test
	public void epochTimeToDateConversion() {
		Long epochTime = 1091160000L;
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault());
		String dateStr = dtf.format(Instant.ofEpochSecond(epochTime));
		
		//System.out.println(dateStr);
		Assert.assertEquals(dateStr, "2004-07-30");
	}
	
	//@Test
	public void testFetchInternationalData() {
		String movieId = "/movies/?page=intl&id=tylerperrysshe'slivingmylife.htm";
		MovieSummary summary = FetchDataDelegates.fetchMovieSummary(movieId);
		System.out.println(summary);
		ArrayList<ForeignData> data = FetchDataDelegates.fetchInternationalDataForMovieID(movieId, summary.getReleaseDate());	//new Long(1091160000L)
		//System.out.println(data);
		
	}
	
	@Test
	public void testFetchWeekendData() {
		String movieId = "/movies/?page=weekend&id=afterhours.htm";	//rrrrrrr
		MovieSummary summary = FetchDataDelegates.fetchMovieSummary(movieId);
		System.out.println(summary);
		ArrayList<WeekendData> data = FetchDataDelegates.fetchWeekendDataForMovieID(movieId);	//new Long(1091160000L)
		System.out.println(data);
		
	}
	
	//@Test
	public void testCharset() throws UnsupportedEncodingException{
		String str = new String("abc");
		String str2 = new String(str.getBytes("UTF-8"));
		System.out.println(str.getBytes("ISO-8859-2").equals(str2));
		//002D
	}
	
	//@Test
	public void testLocalDateParseFunction() {
		LocalDate date = LocalDate.parse("2011-09-09", DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(date);
	}
	
	//@Test
	public void testFetchDailyData() {
		String movieId = "/movies/?id=3:10toyuma57.htm";	//rrrrrrr
		MovieSummary summary = FetchDataDelegates.fetchMovieSummary(movieId);
		System.out.println(summary);
		ArrayList<DailyData> data = FetchDataDelegates.fetchDailyDataForMovieID(movieId);	//new Long(1091160000L)
		System.out.println(data);
	}
	
	//@Test
	public void collatorTest() {
		Collator usCollator = Collator.getInstance(Locale.US);
		 usCollator.setStrength(Collator.PRIMARY);
		 if( usCollator.compare("abc", "ABC") == 0 ) {
		     System.out.println("Strings are equivalent");
		 }
	}
	
	//@Test
	public void testDetermindWeekendEndDateFunctionCaseOne() {
		String wkndDate = "JUL 30-AUG 1";
		String year = "2015";
		LocalDate date = FetchDataDelegates.determindWeekendEndDate(wkndDate, year);
		System.out.println(date.toEpochDay());
		
	}
	
	//@Test
	public void testDetermindWeekendEndDateFunctionCaseTwo() {
		String wkndDate = "Dec 25–27";
		String year = "2015";
		LocalDate date = FetchDataDelegates.determindWeekendEndDate(wkndDate, year);
		System.out.println(date.toEpochDay());
		
	}
	
	@Test
	public void testWeekendFunctionForMovieWithoutWeekendData() {
		System.out.println(FetchDataDelegates.fetchWeekendDataForMovieID("/movies/?id=aashayein.htm"));
	}
	
	
}
