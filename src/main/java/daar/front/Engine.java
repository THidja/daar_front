package daar.front;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import daar.front.beans.Book;


@ManagedBean
public class Engine {
	
	private final static Logger logger = Logger.getLogger(Engine.class);
	// link of the python api
	private static String api = Application.properties.getProperty("BACK_API");
	// result of search
	private static List<Book> books = new ArrayList<>();
	// selected book for suggestions
	private Book selectedBook = null;
	// list of suggestions for selected book
	private static List<Book> suggestions = new ArrayList<>();
	
	
	// simples search
	public static boolean simpleSearch(String word)  {
		// api to call for simple search
		String url = api + "/books/search";
		// data to send
		String json_data = String.format("{word: %s}", word);
		
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.post(url)
							  .header("Content-Type", "application/json")
							  .header("accept", "application/json")
							  .body(new JsonNode(json_data))
							  .asJson();
			int status  = response.getStatus();
			if(status == 200) {
				books = traitCorrectResponse(response);
				return true;
			}
			else {
				logger.error(response.getBody());
			}
		} catch (UnirestException e) {
			logger.error(e);
		}
		return false;
	}
	
	public  List<Book> getSuggestions() {
		if(selectedBook == null) {
			return new ArrayList<>();
		}
		// api to call for simple search
		String url = api + "/books/suggestions";
		// data to send
		String json_data = String.format("{book_id: %d}", selectedBook.getBookId());
		
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.post(url)
							  .header("Content-Type", "application/json")
							  .header("accept", "application/json")
							  .body(new JsonNode(json_data))
							  .asJson();
			int status  = response.getStatus();
			if(status == 200) {
				suggestions = traitCorrectResponse(response);
				return suggestions;
			}
			else {
				logger.error(response.getBody());
			}
		} catch (UnirestException e) {
			logger.error(e);
		}
		return new ArrayList<>();
	}
	
	// advanced search
	public static boolean advancedSearch(String pattern, String title, String author, String language, String subject)  {
		// api to call for advanced search
		String url = api + "/books/advanced_search";
		String json = "{";
		// data to send
		if(!pattern.isEmpty()) {
			json += String.format("pattern:%s,", pattern);
		}
		if(!title.isEmpty()) {
			json += String.format("title:%s,", title);
		}
		if(!author.isEmpty()) {
			json += String.format("author:%s,", author);
		}
		if(!language.isEmpty()) {
			json += String.format("language:%s,", language);
		}
		if(!subject.isEmpty()) {
			json += String.format("subject:%s,", subject);
		}
		if(json.endsWith(",")) {
			json = json.substring(0, json.length() - 1);
		}
		json += "}";
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.post(url)
							  .header("Content-Type", "application/json")
							  .header("accept", "application/json")
							  .body(new JsonNode(json))
							  .asJson();
			int status  = response.getStatus();
			if(status == 200) {
				 books = traitCorrectResponse(response);
				 return true;
			}
			else {
				logger.error(response.getBody());
			}
		} catch (UnirestException e) {
			logger.error(e);
		}
		return false;
	}
	
	private static List<Book> traitCorrectResponse(HttpResponse<JsonNode> response) {
		JSONArray res = response.getBody().getArray();
		List<Book> responses = new ArrayList<>();
		res.forEach(object -> {
			JSONObject node = (JSONObject) object;
			int bookId = node.getInt("id");
			String title = node.getString("title");
			String authors = node.getString("authors");
			String subjects = node.getString("subjects");
			String languages = node.getString("languages");
			String link = node.getString("link");
			String coverLink = extractCoverLink(node.getString("cover_link"));
			Book r = new Book(bookId, title, authors, subjects, languages, link, coverLink);
			responses.add(r);
		});
		return responses;
	}
	
	private static String extractCoverLink(String coverLink) {
		if(!coverLink.isEmpty()) {
			return coverLink;
		}
		return "img/default_cover.jpg";
		
	}
	
	// getters and setters
	public List<Book> getBooks() {
		return books;
	}
	
	public Book getSelectedBook() {
		return selectedBook;
	}
	
	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	
}