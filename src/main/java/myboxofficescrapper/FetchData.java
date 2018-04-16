package myboxofficescrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import util.Constants;
import beans.DailyData;
import beans.ForeignData;
import beans.MovieSummary;
import beans.WeekendData;
import beans.WeeklyData;

public class FetchData {
	
	private static final Logger logger = (Logger) LogManager.getLogger(FetchData.class);
	
	// available list of alphabet/special character(s)
	private static ArrayList<String> alphabeticalList = new ArrayList<>();

	// categories for each alphabet/special character(s)
	private static ArrayList<String> alphabetCategory = new ArrayList<>();

	// movie IDs read for reach alphbaet category
	private static ArrayList<String> movieIds = new ArrayList<>();
	
	private static ArrayList<MovieSummary> movieSummary = new ArrayList<>();
	private static ArrayList<DailyData> dailyData = new ArrayList<>();
	private static ArrayList<ForeignData> intlData = new ArrayList<>();
	private static ArrayList<WeekendData> wkndData = new ArrayList<>();
	private static ArrayList<WeeklyData> wklyData = new ArrayList<>();
	
	private static SessionFactory sessionFactory;
	
	public static void initHibernate() {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("database/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	/*
	 * saveDataToDB
	 * 
	 */
	public static void saveDataToDB() throws Exception {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		if(movieSummary.size() > 0) {
			for(MovieSummary sum : movieSummary) {
				logger.info(movieSummary);
				if(sum != null)
					session.save(sum);
			}
		}
		if(intlData.size() > 0) {
			for(ForeignData intl : intlData) {
				logger.info(intl);
				if(intl != null && intl.getFDKey() != null)
					session.save(intl);
			}
		}
			
		if(dailyData.size() > 0) {
			for(DailyData daily : dailyData) {
				logger.info(daily);
				if(daily != null && daily.getDailyDataId() != null)
					session.save(daily);
			}
		}
		if(wkndData.size() > 0) {
			for(WeekendData wknd: wkndData) {
				logger.info(wknd);
				if(wknd != null)
					session.save(wknd);
			}
		}
		if(wklyData.size() > 0) {
			for(WeeklyData wkly: wklyData) {
				logger.info(wkly);
				if(wkly != null)
					session.save(wkly);
			}
		}
		session.getTransaction().commit();
		session.close();
		
		// RESET FOR NEXT ITERATION
		movieSummary = new ArrayList<>();
		dailyData = new ArrayList<>();
		intlData = new ArrayList<>();
		wkndData = new ArrayList<>();
		wklyData = new ArrayList<>();
	}
	
	/**
	 * getHTMLFromURL: Given a URL, open a connection to the URL, retrieve the HTML with ISO-8859-2 encoding and return it. 
	 * @param url : link to parse
	 * @return
	 */
	public static StringBuilder getHTMLFromURL(String url) {
		//logger.info("Entered getHTMLFromURL. Received URL: " + url + ". Opening a connection...");
		
		StringBuilder sb = new StringBuilder();
		InputStream is;
		
		try {
			URL urll = new URL(url);
			HttpURLConnection huc =  (HttpURLConnection)  urll.openConnection (); 
			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
			huc.connect() ; 
			int code = huc.getResponseCode() ;
			if(code >= 200 && code <= 205) {
				is = new URL(url).openStream();
				
				//logger.info("Opened connection. Ready to read the HTML...");
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Constants.HTMLEncoding), 8 * 1024);
				sb = new StringBuilder();
				String line = "";
				
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		//logger.info("Leaving getHTMLFromURL. Read HTML from URL: " + url);
		return sb;
	}

	/**
	 * initializeAlphabetRow - given the root element of the document, 
	 * determine location of the row with alphabets (and special characters) by which movie titles begin
	 * and fill the map with the data.
	 * @param root
	 * @return
	 */
	public static ArrayList<String> initializeAlphabetRow(Element root) {
		ArrayList<String> result = new ArrayList<>();
		Element alphabetTableRow = root.getElementsByTag("table").get(2).getElementsByTag("tr").get(1);
		
		alphabetTableRow.children().forEach((x) -> {
			Element el = (Element)x;
			// IF SELECTED, ATTRIBUTE "bgcolor" HAS A VALUE OF "#ffffff"
			// IF NOT SELECTED, ATTRIBUTE "bgcolor" HAS A VALUE OF "#dcdcdc"
			if(el.getElementsByTag("td").attr("bgcolor").equals("#dcdcdc")) {
				result.add(Constants.mojoLink + el.getElementsByTag("a").get(0).attr("href"));	//, Constants.NOTVISITED);	
			}/* else if(el.getElementsByTag("td").attr("bgcolor").equals("#ffffff")) {
				result.put(Constants.mojoLink + el.getElementsByTag("a").get(0).attr("href"), Constants.ACTIVE);
			}*/
		});		
		
		return result;
	}
	
	/**
	 * initializeAlphabetCatgeory - given the current document, and the main alphabetical list, determine the categories (if exists)
	 * @param alphabetList
	 * @param doc
	 * @return
	 */
	public static ArrayList<String> initializeAlphabetCatgeory(ArrayList<String> alphabetList, org.jsoup.nodes.Document doc) {
		ArrayList<String> result = new ArrayList<>();
		
		//alphabetList.keySet().forEach((x) -> {
			// HIT EACH OF THE LINKS IN THE LIST
									
		List<Node> alphabetCategoryList = doc.getElementById(Constants.rootElementInBaseLink).getElementsByClass("alpha-nav-holder").get(0).childNodes();
			if(alphabetCategoryList != null) {
				alphabetCategoryList.get(0).childNodes().forEach((y) -> {
					if(y.childNodeSize() > 0)			
						if(y.childNode(0).hasAttr("href")) {
							result.add(Constants.mojoLink + y.childNode(0).attr("href")); //, Constants.NOTVISITED);
						}/* else {
							result.put(y.childNode(0).outerHtml(), Constants.ACTIVE);
						}*/
				});
			}
		//});
		
		return result;
	}
	
	/**
	 * readMovieIdsInDoc - given an element as the starting point, find the table with movie IDs,
	 * and scrap the data off the page.
	 * @param rootInBase
	 * @return
	 */
	public static ArrayList<String> readMovieIdsInDoc(Element rootInBase) {
		ArrayList<String> result = new ArrayList<>();
		Element tableOfMovies = rootInBase.getElementsByTag("table").get(3).child(0);
		
		tableOfMovies.getElementsByTag("tr").forEach((x) -> {
			Element el = (Element) x;
			// ENSURE THAT WE IGNORE THE TABLE HEADING "Title (click to view box office)" -- WHICH WILL NOT CONTAIN A LINK
			if(!el.child(0).getElementsByTag("a").attr("href").isEmpty()) {		
				result.add(el.child(0).getElementsByTag("a").attr("href")); //, el.child(0).text());
			}
		});
		//logger.info(result.toString());
		return result;
	}
	
	public static void getData(org.jsoup.nodes.Document doc, Element rootInBase) throws MalformedURLException, IOException {
		alphabetCategory.add(0, doc.location());		// ADD THE CURRENT URI AS IT WON'T BE ADDED BY 'initializeAlphabetCatgeory' 
		alphabetCategory.addAll(initializeAlphabetCatgeory(alphabeticalList, doc));		// ADD THE HYPERLINKS		
		HashMap<Integer, ArrayList<String>> hashVals = new HashMap<>(); 
		// FINALLY, READ THE MOVIEIDS FOR EACH ALPHABET CATEGORY
		for(String alphabetCat : alphabetCategory) {
			String nextUnvisitedURL = (alphabetCat != null && alphabetCat.length() > 0 ? alphabetCat : null);
			if(nextUnvisitedURL != null && nextUnvisitedURL.length() > 0) {
				
				HttpURLConnection huc =  ( HttpURLConnection )  new URL(nextUnvisitedURL).openConnection (); 
				huc.setRequestMethod ("GET"); 
				huc.connect() ; 
				int code = huc.getResponseCode() ;
				
				if(code >= 200 && code <= 202) {
					doc = org.jsoup.Jsoup.parse(new URL(nextUnvisitedURL).openStream(), Constants.HTMLEncoding, nextUnvisitedURL);
					rootInBase = doc.getElementById(Constants.rootElementInBaseLink);
				}
			}
			ArrayList<String> moviedata = readMovieIdsInDoc(rootInBase);
			if(hashVals.containsKey(moviedata.hashCode())) {
				logger.error("FOUND DUPLICATE");
				hashVals.get(moviedata.hashCode());
			} else {
				hashVals.put(moviedata.hashCode(), moviedata);
				movieIds.addAll(moviedata);
			}
		}
		
		movieIds.forEach((movieId) -> {
			
			logger.info("MOVIE ID IS " + movieId);
			MovieSummary summary = FetchDataDelegates.fetchMovieSummary(movieId);
			if(summary.getMovieID() != null && summary.getMovieID().length() > 0) {
				dailyData.addAll(FetchDataDelegates.fetchDailyDataForMovieID(movieId));
				wkndData.addAll(FetchDataDelegates.fetchWeekendDataForMovieID(movieId));
				wklyData.addAll(FetchDataDelegates.fetchWeeklyDataForMovieID(movieId));
				intlData.addAll(FetchDataDelegates.fetchInternationalDataForMovieID(summary.getMovieID(), summary.getReleaseDate()));
				movieSummary.add(summary);
				try {
					if(movieSummary.size() > 100)
						saveDataToDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*Movie movie = new Movie(summary, 
						FetchDataDelegates.fetchDailyDataForMovieID(movieId), 
						FetchDataDelegates.fetchWeekendDataForMovieID(movieId),
						FetchDataDelegates.fetchWeeklyDataForMovieID(movieId),
						FetchDataDelegates.fetchInternationalDataForMovieID(summary.getMovieID(), summary.getReleaseDate()));
				logger.info(movie);
				movies.add(movie);*/
			}
		});
		
		// SAVE THE LAST FEW ROWS
		try {
			saveDataToDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * MAIN: DRIVER
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			initHibernate();
			
			// STEP 1: Start by generating a link to parse.
			org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(new URL(Constants.baseLink).openStream(), Constants.HTMLEncoding, Constants.baseLink);
						
			// WEB PAGE HAVE 5 TABLES. 1 & 2 ARE OF NO USE.
			// TABLE 3 CONTAINS THE ALPHABETICAL LIST
			// TABLE 4 CONTAINS THE SUB CATEGORIES
			// TABLE 5 CONTAINS THE ACTUAL LIST OF MOVIES
			Element rootInBase = doc.getElementById(Constants.rootElementInBaseLink);
			
			alphabeticalList = initializeAlphabetRow(rootInBase);
			
			// LOOP OVER THE ALPHABETICAL LIST
			for(String alphabet: alphabeticalList) {
								
				// LOOP OVER ALL THE ELEMENTS IN THE ALPHABETICAL LIST, AND
				// INITIALIZE THE ALPHABET CATEGORY MAP FOR THE ACTIVE ALPHABET
				//alphabetCategory.add(0, doc.location());		// ADD THE CURRENT URI AS IT WON'T BE ADDED BY 'initializeAlphabetCatgeory' 
				//alphabetCategory.addAll(initializeAlphabetCatgeory(alphabeticalList, doc));		// ADD THE HYPERLINKS		

				// FOR THE GIVEN WEB PAGE, AND IT'S ROOT LOOP OVER ALL THE MOVIES AND FETCH DATA.
				getData(doc, rootInBase);

				// UPDATE DOC WITH THE NEXT ALPHABET IN LINE
				String nextUnvisitedURL = (alphabet != null && alphabet.length() > 0 ? alphabet : null);
				//if(nextUnvisitedURL != null) 
				doc = org.jsoup.Jsoup.parse(new URL(nextUnvisitedURL).openStream(), Constants.HTMLEncoding, nextUnvisitedURL);
				rootInBase = doc.getElementById(Constants.rootElementInBaseLink);
				
				alphabetCategory.clear();		// PREP FOR NEXT ITERATION
				movieIds.clear();
			}
			
			getData(doc, rootInBase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
