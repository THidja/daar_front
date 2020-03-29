package daar.front.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import daar.front.Engine;

@ManagedBean(name="asForm")
public class AdvancedSearchForm {
	
	// form fiedls
	private String pattern;
	private String title;
	private String subject;
	private String language;
	private String author;
	
	private List<String> languages;
	private Map<String, String> languagesCodes;
	
	public AdvancedSearchForm() {
		Locale appLocale = new Locale("en_US");
		languages = new ArrayList<>();
		languagesCodes = new HashMap<>();
		// first add empty choise
		languages.add("");
		languagesCodes.put("", "");
		
		Arrays.asList(Locale.getISOLanguages())
			   .forEach(lang -> {
				   String language = new Locale(lang).getDisplayLanguage(appLocale);
				   languages.add(language);
				   languagesCodes.put(language, lang);  
			   });
	}
	
	
	public String submit() throws IOException {
		String formData = pattern + title + subject + language + author;
		formData = formData.trim();
		
		if(formData.length() > 0) {
			language = languagesCodes.get(language);
			if(Engine.advancedSearch(pattern, title, author, language, subject)) {
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				context.redirect(context.getRequestContextPath() + "/result.xhtml");
			}
		}
		return "";
	}
	
	// getters and setters
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	// books filter languages
	public List<String> getLanguages() {
		return languages;
	}
	
}
