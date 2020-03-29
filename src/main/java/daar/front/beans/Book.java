package daar.front.beans;

// book bean
public class Book {
	
	private int bookId;
	private String title;
	private String authors;
	private String subjects;
	private String languages;
	private String link;
	private String coverLink;
	// construcor
	public Book(int bookId, String title, String authors, String subjects, String languages, String link,
			String coverLink) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authors = authors;
		this.subjects = subjects;
		this.languages = languages;
		this.link = link;
		this.coverLink = coverLink;
	}
	// getters and setters
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCoverLink() {
		return coverLink;
	}
	public void setCoverLink(String coverLink) {
		this.coverLink = coverLink;
	}
	// string representation
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", subjects=" + subjects
				+ ", languages=" + languages + ", link=" + link + ", coverLink=" + coverLink + "]";
	}
}